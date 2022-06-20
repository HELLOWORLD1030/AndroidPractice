package top.zzgpro.androidpractice.Item;

public class GoodsItem {
    private String name;
    private String sellcount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSellcount() {
        return sellcount;
    }

    public void setSellcount(String sellcount) {
        this.sellcount = sellcount;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    private String picture;

    @Override
    public String toString() {
        return "GoodsItem{" +
                "name='" + name + '\'' +
                ", sellcount='" + sellcount + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
