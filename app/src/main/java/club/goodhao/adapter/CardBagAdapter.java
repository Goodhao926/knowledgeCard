package club.goodhao.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import club.goodhao.R;
import club.goodhao.activity.CardActivity;
import club.goodhao.entity.ItemCardBag;
import club.goodhao.model.CardBag;
import club.goodhao.utils.MyApplication;

public class CardBagAdapter extends RecyclerView.Adapter<CardBagAdapter.ViewHolder> {
    private List<ItemCardBag> mItemCardBagList;
    private String TAG = "CardBagActivity";

    public CardBagAdapter(List<ItemCardBag> itemCardBags) {
        mItemCardBagList = itemCardBags;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView TextName, TextNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            TextName = (TextView) itemView.findViewById(R.id.tv_card_bag_name);
            TextNumber = (TextView) itemView.findViewById(R.id.tv_card_bag_number);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_bag, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + MyApplication.getContext());

                Intent intent = new Intent(MyApplication.getContext(), CardActivity.class);
                CardActivity.card = mItemCardBagList.get(holder.getLayoutPosition());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MyApplication.getContext().startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemCardBag itemCardBag = mItemCardBagList.get(position);
        holder.TextName.setText(itemCardBag.getName());
        holder.TextNumber.setText(itemCardBag.getNumber());
        holder.view.setBackgroundColor(Color.parseColor(itemCardBag.getBgColor()));
    }

    @Override
    public int getItemCount() {
        return mItemCardBagList.size();
    }


}
