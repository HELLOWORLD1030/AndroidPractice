package top.zzgpro.androidpractice.Item;

public class BaseAdapterItem {
    private int icon; // 图标数据
    private String title; // 标题数据
    private String info; // 信息描述数据

    // 构造方法
    public BaseAdapterItem() {
    }
    public BaseAdapterItem(int icon, String title, String info) {
        this.icon = icon;
        this.title = title;
        this.info = info;
    }

    // Getter和Setter方法
    public int getIcon() {
        return icon;
    }
    public void setIcon(int icon) {
        this.icon = icon;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }

}
