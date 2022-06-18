package top.zzgpro.androidpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

import top.zzgpro.androidpractice.Adapter.RecyclerDecoration;
import top.zzgpro.androidpractice.Adapter.RecyclerViewAdapter;
import top.zzgpro.androidpractice.Item.RecyclerItemData;
import top.zzgpro.androidpractice.Adapter.RecyclerViewAdapter;
import top.zzgpro.androidpractice.Item.RecyclerItemData;

public class ActivityMall extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall);
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.malllist);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        RecyclerViewAdapter recyclerViewAdapter=new RecyclerViewAdapter(this,initDatas());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recyclerViewAdapter);
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put(RecyclerDecoration.BOTTOM_DECORATION,70);//右间距
        stringIntegerHashMap.put(RecyclerDecoration.RIGHT_DECORATION,150);
        recyclerView.addItemDecoration(new RecyclerDecoration(stringIntegerHashMap));
    }
    private ArrayList<RecyclerItemData> initDatas() {
        ArrayList<RecyclerItemData> mDatas = new ArrayList<>();
       mDatas.add(new RecyclerItemData(R.drawable.bg1,"拍立得","抑或是都卡死了"));
        mDatas.add(new RecyclerItemData(R.drawable.bg1,"拍立得","抑或是都卡死了"));
        mDatas.add(new RecyclerItemData(R.drawable.bg1,"拍立得123","抑或是都卡死了"));
        mDatas.add(new RecyclerItemData(R.drawable.bg1,"拍立得","抑或是都卡死了"));
        mDatas.add(new RecyclerItemData(R.drawable.bg1,"拍立得","抑或是都卡死了"));
        mDatas.add(new RecyclerItemData(R.drawable.bg1,"拍立得123","抑或是都卡死了"));
        mDatas.add(new RecyclerItemData(R.drawable.bg1,"拍立得","抑或是都卡死了"));
        mDatas.add(new RecyclerItemData(R.drawable.bg1,"拍立得","抑或是都卡死了"));
        mDatas.add(new RecyclerItemData(R.drawable.bg1,"拍立得123","抑或是都卡死了"));
        mDatas.add(new RecyclerItemData(R.drawable.bg1,"拍立得","抑或是都卡死了"));
        mDatas.add(new RecyclerItemData(R.drawable.bg1,"拍立得","抑或是都卡死了"));
        mDatas.add(new RecyclerItemData(R.drawable.bg1,"拍立得123","抑或是都卡死了"));
        return mDatas;
    }


}