package club.goodhao.entity;

public class ItemCardBag {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public ItemCardBag() {
    }

    public ItemCardBag(int id, String type, String name, String number, String bgColor) {
        this.id = id;
        Type = type;
        this.name = name;
        this.number = number;
        this.bgColor = bgColor;
    }

    private String Type;
    private String name;

    private String number;
    private String bgColor;

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
