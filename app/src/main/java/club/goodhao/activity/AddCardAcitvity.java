package club.goodhao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import club.goodhao.R;
import club.goodhao.model.Card;
import club.goodhao.model.CardBag;

public class AddCardAcitvity extends BaseActivity {
    private String TAG = "AddCardAcitvity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        initView();


    }

    private void initView() {
        RelativeLayout btn_add = (RelativeLayout) findViewById(R.id.btn_new);
        EditText et_back = (EditText) findViewById(R.id.et_back);
        EditText et_front = (EditText) findViewById(R.id.et_front);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Card card = new Card();
                CardBag cardbag = LitePal.find(CardBag.class, CardActivity.card.getId(),true);
                card.setCardBag(cardbag);
                card.setFront(et_front.getText().toString());
                card.setBack(et_back.getText().toString());
                card.save();
                finish();
            }
        });

    }
}
