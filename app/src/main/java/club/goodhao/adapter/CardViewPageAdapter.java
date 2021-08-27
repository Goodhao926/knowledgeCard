package club.goodhao.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.viewpager.widget.PagerAdapter;

import org.litepal.LitePal;

import java.util.List;

import club.goodhao.R;
import club.goodhao.entity.ItemCard;
import club.goodhao.listen.CardListener;
import club.goodhao.model.Card;
import club.goodhao.utils.MyApplication;

public class CardViewPageAdapter extends PagerAdapter {
    private static final String TAG = "CardViewPageAdapter";
    private List<ItemCard> itemCardList;
    private CardListener cardViewListener;

    public CardViewPageAdapter(List<ItemCard> itemCardList) {
        this.itemCardList = itemCardList;


    }


    @Override
    public int getCount() {
        return itemCardList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = View.inflate(MyApplication.getContext(), R.layout.item_card, null);
        ItemCard itemCard = itemCardList.get(position);
        TextView front_tv = (TextView) view.findViewById(R.id.front_tv);
        TextView back_tv = (TextView) view.findViewById(R.id.back_tv);
        CardView front = (CardView) view.findViewById(R.id.front);
        CardView back = (CardView) view.findViewById(R.id.back);
        front_tv.setText(itemCardList.get(position).getFront());
        back_tv.setText(itemCardList.get(position).getBack());
        ImageView iv_delete = view.findViewById(R.id.iv_delete);
        ImageView iv_voic = view.findViewById(R.id.iv_voice);
        back.setRotationY(-180);
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 动画
                int distance = 16000;
                float scale = MyApplication.getContext().getResources().getDisplayMetrics().density * distance;
                // 设置相机距离
                front.setCameraDistance(scale);
                back.setCameraDistance(scale);
                front.setElevation(0);
                back.setElevation(0);
                if (itemCard.isFront()) {
                    front.animate().rotationY(180).alpha(0).setInterpolator(new FastOutLinearInInterpolator()).start();
                    back.animate().rotationY(0).setInterpolator(new FastOutLinearInInterpolator()).start();
                    itemCard.setFront(false);
                } else {
                    front.animate().rotationY(0).alpha(1).setInterpolator(new FastOutLinearInInterpolator()).start();
                    back.animate().rotationY(-180).setInterpolator(new FastOutLinearInInterpolator()).start();
                    itemCard.setFront(true);

                }


                front.animate().setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        front.setElevation(8);
                        back.setElevation(8);
                    }
                });
                back.animate().setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        front.setElevation(8);
                        back.setElevation(8);
                    }
                });
            }
        });

        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(container.getContext());
                builder.setTitle("提示")
                        .setMessage("确认删除该卡片吗?")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LitePal.delete(Card.class, itemCard.getId());
                                cardViewListener.onDeleteCardClick(itemCard);
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
        });
        iv_voic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardViewListener.onVoiceClick(itemCard);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public float getPageWidth(int position) {
        return 1f;
    }

    public void setCardViewListener(CardListener listener) {
        cardViewListener = listener;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
