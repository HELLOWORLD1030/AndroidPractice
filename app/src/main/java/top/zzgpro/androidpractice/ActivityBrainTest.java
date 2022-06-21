package top.zzgpro.androidpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import top.zzgpro.androidpractice.Adapter.BrainTestAdapter;
import top.zzgpro.androidpractice.Item.BrainTestItem;

public class ActivityBrainTest extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_braintest);
        RecyclerView recyclerView=findViewById(R.id.ReclyclerViewTest1);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        BrainTestAdapter recyclerViewAdapter=new BrainTestAdapter(this,initDatas());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
    /**
     * 初始化 问题及答案数组
     * @return 包含问题及答案的ArrayList
     */
    private ArrayList<BrainTestItem> initDatas(){
        ArrayList<BrainTestItem> list=new ArrayList<>();
        list.add(new BrainTestItem("什么门永远关不上","球门"));
        list.add(new BrainTestItem("什么东西没人爱吃？","亏"));
        list.add(new BrainTestItem("什么瓜不能吃？","傻瓜"));
        list.add(new BrainTestItem("什么布切不断？","瀑布"));
        list.add(new BrainTestItem("什么鼠最爱干净?","环保署"));
        list.add(new BrainTestItem("偷什么不犯法？","偷笑"));
        return list;
    }
}