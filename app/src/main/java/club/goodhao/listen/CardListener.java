package club.goodhao.listen;

import club.goodhao.entity.ItemCard;
import club.goodhao.model.Card;

public interface CardListener {
    public void onDeleteCardClick(ItemCard itemCard);
    public void onVoiceClick(ItemCard itemCard);
}
