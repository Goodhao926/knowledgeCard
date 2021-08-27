package club.goodhao.entity;

public class ItemCard {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String front;
    private String back;

    public String getFront() {
        return front;
    }

    public ItemCard(int id, String front, String back) {
        this.id = id;
        this.front = front;
        this.back = back;
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

    public boolean isFront() {
        return isFront;
    }

    public void setFront(boolean front) {
        isFront = front;
    }

    private boolean isFront = true;
}
