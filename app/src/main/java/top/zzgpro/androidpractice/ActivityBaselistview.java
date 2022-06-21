package top.zzgpro.androidpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import top.zzgpro.androidpractice.Adapter.CustomBaseAdapter;
import top.zzgpro.androidpractice.Item.BaseAdapterItem;

public class  ActivityBaselistview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baselistview);
        LoadBaseAdapter();
    }
    private void LoadBaseAdapter(){
        ListView listView=findViewById(R.id.baselistview);
        listView.setAdapter(new CustomBaseAdapter(this,this.getItem(),R.layout.baseadapteritem));
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            BaseAdapterItem item=getItem().get(i);
            Toast.makeText(ActivityBaselistview.this,item.getTitle(),Toast.LENGTH_SHORT).show();
        });
    }
    private List<BaseAdapterItem> getItem(){
        List<BaseAdapterItem> items=new ArrayList<>();
        items.add(new BaseAdapterItem(R.drawable.linuxprobe,"Linux就该这么学",""));
        items.add(new BaseAdapterItem(R.drawable.niaoge,"鸟哥的Linux私房菜",""));
        items.add( new BaseAdapterItem(R.drawable.csapp,"深入理解计算机系统",""));
        items.add( new BaseAdapterItem(R.drawable.code,"编码 隐匿在计算机软硬件背后的语言",""));
        items.add(new BaseAdapterItem(R.drawable.cprimerplus,"C Primer Plus",""));
        items.add( new BaseAdapterItem(R.drawable.java,"Java核心技术",""));

        return items;

    }
}