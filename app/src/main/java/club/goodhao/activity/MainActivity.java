package club.goodhao.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dingmouren.colorpicker.ColorPickerDialog;
import com.dingmouren.colorpicker.OnColorPickerListener;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import club.goodhao.R;
import club.goodhao.adapter.CardBagAdapter;
import club.goodhao.entity.ItemCardBag;
import club.goodhao.model.Card;
import club.goodhao.model.CardBag;
import club.goodhao.utils.MyApplication;
import club.goodhao.utils.QRUtil;

public class MainActivity extends BaseActivity {
    private static final int PERMISSION_REQUESTCODE = 100;
    private RecyclerView recyclerView;
    private List<ItemCardBag> cardBagList = new ArrayList<>();
    private CardBagAdapter cardBagAdapter;
    private String TAG = "mainActivty";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        init();
    }

    private void initData() {
        cardBagList.clear();
        List<CardBag> cardBags = LitePal.findAll(CardBag.class);
        for (int i = 0; i < cardBags.size(); i++) {
            CardBag cardBag = cardBags.get(i);
            cardBagList.add(new ItemCardBag(cardBag.getId(), "none", cardBag.getName(),
                    String.valueOf(LitePal.where("cardbag_id=" + cardBag.getId()).count(Card.class)),
                    cardBag.getBgColor()));
        }
    }

    private void init() {

        recyclerView = (RecyclerView) findViewById(R.id.rv_card_bag);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        cardBagAdapter = new CardBagAdapter(cardBagList);
        recyclerView.setAdapter(cardBagAdapter);
        ImageView btn_plus = findViewById(R.id.btn_plus);
        ImageView btn_scan = findViewById(R.id.iv_scan);
        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new EditCardBagDialog(v.getContext()).setListener(new EditCardBagDialog.Listener() {
                    @Override
                    public void onConfrimItem(ItemCardBag itemCardBag) {
                        CardBag cardBag;
                        if (itemCardBag.getId() > 0) {
                            cardBag = LitePal.find(CardBag.class, itemCardBag.getId());
                        } else {
                            cardBag = new CardBag();
                        }
                        cardBag.setBgColor(itemCardBag.getBgColor());
                        cardBag.setName(itemCardBag.getName());
                        cardBag.setCreate_time(new Date());
                        cardBag.save();
                        // flush
                        initData();
                        cardBagAdapter.notifyDataSetChanged();
                    }
                }).show();
            }
        });

        // event bind
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission();
                QRUtil.start();
            }
        });


    }

    private void requestPermission(){
        // 申请权限
        String[] strings = {Manifest.permission.CAMERA};
        if(ContextCompat.checkSelfPermission(MyApplication.getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,strings
                    , PERMISSION_REQUESTCODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e(TAG, "onRequestPermissionsResult: " + requestCode );
    }
}

class EditCardBagDialog {
    private ItemCardBag itemCardBag;
    private Context context;
    private String bgColor;
    private Listener listener;

    public EditCardBagDialog(Context context, ItemCardBag itemCardBag) {
        this.itemCardBag = itemCardBag;
        this.context = context;
    }

    public EditCardBagDialog(Context context) {
        this.context = context;
        this.itemCardBag = new ItemCardBag();
    }

    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dialog_add_cardbag, null);
        EditText editText = view.findViewById(R.id.et_cardbag_name);
        View colorView = view.findViewById(R.id.v_cardbag_color);
        colorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ColorPickerDialog(
                        context, Color.parseColor("#A5E196"), true,
                        new OnColorPickerListener() {
                            @Override
                            public void onColorCancel(ColorPickerDialog dialog) {

                            }

                            @Override
                            public void onColorChange(ColorPickerDialog dialog, int color) {

                            }

                            @Override
                            public void onColorConfirm(ColorPickerDialog dialog, int color) {
                                bgColor = String.format("#%06X", (0xFFFFFF & color));
                                colorView.setBackgroundColor(color);
                            }
                        }
                ).show();

            }
        });
        Log.e("EditCardBag", "show: " + itemCardBag.getId());

        if (itemCardBag.getId() != 0) {
            editText.setText(itemCardBag.getName());
            colorView.setBackgroundColor(Color.parseColor(itemCardBag.getBgColor()));
        }
        builder.setView(view).
                setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String s = editText.getText().toString();
                        if (!s.equals("")) {
                            EditCardBagDialog.this.itemCardBag.setName(s);
                        }
                        if (bgColor != null) {
                            EditCardBagDialog.this.itemCardBag.setBgColor(bgColor);
                        }

                        listener.onConfrimItem(itemCardBag);

                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).show();
    }

    interface Listener {
        public void onConfrimItem(ItemCardBag itemCardBag);
    }

    public EditCardBagDialog setListener(Listener listener) {
        this.listener = listener;
        return this;
    }
}