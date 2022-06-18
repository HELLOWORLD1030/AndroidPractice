package top.zzgpro.androidpractice.Item;

public class RecyclerItemData {
    private int Image;

    public int getImage() {
        return Image;
    }

    public String getTitle() {
        return Title;
    }

    public String getSellCount() {
        return sellCount;
    }

    private String Title;
    private String sellCount;
    public RecyclerItemData(int image, String Name,String sellCount){
        this.Image=image;
        this.Title=Name;
        this.sellCount=sellCount;
    }
}