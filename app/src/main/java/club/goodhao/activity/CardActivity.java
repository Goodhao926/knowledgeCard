package club.goodhao.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import club.goodhao.R;
import club.goodhao.adapter.CardViewPageAdapter;
import club.goodhao.entity.ItemCard;
import club.goodhao.entity.ItemCardBag;
import club.goodhao.listen.CardListener;
import club.goodhao.model.Card;
import club.goodhao.model.CardBag;
import club.goodhao.utils.MediaHelper;
import club.goodhao.utils.MyApplication;

public class CardActivity extends BaseActivity {
    private static final String TAG = "CardActivity";
    public static ItemCardBag card;
    private ViewPager viewPager;
    private List<ItemCard> itemCardList = new ArrayList<>();
    private CardViewPageAdapter cardViewPageAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<Card> cardList = LitePal.find(CardBag.class, card.getId(),true).getCardList();
        if(!cardList.isEmpty()){
            itemCardList.clear();
            for(Card c :cardList){
                itemCardList.add(new ItemCard(c.getId(),c.getFront(),c.getBack()));
            }
            Log.e(TAG, "onStart: itemCardList-len:" + itemCardList.size() );
            cardViewPageAdapter.notifyDataSetChanged();
        }

    }

    private void init() {
        viewPager = (ViewPager) findViewById(R.id.vp_card);
        TextView title  = findViewById(R.id.tv_card_bag_title);
        Intent intent = getIntent();
        cardViewPageAdapter = new CardViewPageAdapter(itemCardList);
        Log.e(TAG, "init: adapter");
        viewPager.setAdapter(cardViewPageAdapter);
        viewPager.setPageMargin(20);
        viewPager.setOffscreenPageLimit(3);
        title.setText(card.getName());
        RelativeLayout addView =(RelativeLayout) findViewById(R.id.rl_add);

        cardViewPageAdapter.setCardViewListener(new CardListener() {
            @Override
            public void onDeleteCardClick(ItemCard itemCard) {
                itemCardList.remove(itemCard);
                Log.e(TAG, "onStart: itemCardList-len:" + itemCardList.size() );
                cardViewPageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onVoiceClick(ItemCard itemCard) {
                Log.e(TAG, "onVoiceClick: voice");
                MediaHelper.playWord(MediaHelper.EN,itemCard.getFront());
            }
        });

        addView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CardActivity.this, AddCardAcitvity.class);
                startActivity(intent);
            }
        });




    }
}
