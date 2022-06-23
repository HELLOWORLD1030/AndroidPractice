package top.zzgpro.androidpractice.Bean;

import android.graphics.Bitmap;

public class msgBean {
    private int type;
    private String text;
    private Bitmap icon;

    //返回类型  用于检测接收数据类型
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    //接收消息内容
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    //位图 用于存放头像
    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

}