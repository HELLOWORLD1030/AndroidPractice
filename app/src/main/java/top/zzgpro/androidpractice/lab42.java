package top.zzgpro.androidpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import top.zzgpro.androidpractice.Adapter.Lab42Adapter;

public class lab42 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab42);
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.ReclyclerViewTest1);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        Lab42Adapter recyclerViewAdapter=new Lab42Adapter(this,initDatas(),initAnswers());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recyclerViewAdapter);

    }
    private ArrayList<String> initDatas(){
        ArrayList<String> list=new ArrayList<>();
        list.add("什么门永远关不上");
        list.add("什么东西没人爱吃？");
        list.add("什么瓜不能吃？");
        list.add("什么布切不断？");
        list.add("什么鼠最爱干净?");
        list.add("偷什么不犯法？");
        return list;
//                ",  "","",  ？",""};
    }
    private String[] initAnswers(){
        String[] list={ "球门", "亏",  "傻瓜","瀑布",  " 环保署","偷笑" };
        return list;

    }
}