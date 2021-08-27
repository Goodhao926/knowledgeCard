package club.goodhao.model;

import org.litepal.crud.LitePalSupport;

public class Card extends LitePalSupport {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String front;
    private String back;
    private CardBag cardBag;

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public CardBag getCardBag() {
        return cardBag;
    }

    public void setCardBag(CardBag cardBag) {
        this.cardBag = cardBag;
    }
}
