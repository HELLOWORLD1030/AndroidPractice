package top.zzgpro.androidpractice.Item;

public class RecyclerItemData {
    private String Image;

    public String getImage() {
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
//    public RecyclerItemData(int image, String Name,String sellCount){
//        this.Image=image;
//        this.Title=Name;
//        this.sellCount=sellCount;
//    }
    public RecyclerItemData(String imageURL,String name,String sellCount){
        this.Image=imageURL;
        this.Title=name;
        this.sellCount=sellCount;
    }
}