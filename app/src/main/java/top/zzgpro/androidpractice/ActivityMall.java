package top.zzgpro.androidpractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import top.zzgpro.androidpractice.Adapter.RecyclerDecoration;
import top.zzgpro.androidpractice.Adapter.MallAdapter;
import top.zzgpro.androidpractice.Item.GoodsItem;

public class ActivityMall extends AppCompatActivity {
    private final String BaseURL="https://web.zzgpro.top/";
    private MallAdapter mallAdapter;
    private final ArrayList<GoodsItem> mDatas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall);
        RecyclerView recyclerView= findViewById(R.id.malllist);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        Log.d("networkRecyclerview",String.valueOf( mDatas.size()));
        mallAdapter =new MallAdapter(this,mDatas);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mallAdapter);
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put(RecyclerDecoration.BOTTOM_DECORATION,70);//右间距
        stringIntegerHashMap.put(RecyclerDecoration.RIGHT_DECORATION,150);
        recyclerView.addItemDecoration(new RecyclerDecoration(stringIntegerHashMap));
        initDatas();
        /*
           设置滑动到底部的时候加载下一批数据
         */
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    initDatas();
                }
            }
        });
        /*
         * 设置下拉刷新监听器
         */
        SwipeRefreshLayout swipeRefreshLayout=findViewById(R.id.mainrefresh);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            mallAdapter.resetData();
            initDatas();
            swipeRefreshLayout.setRefreshing(false);
        });
    }
    @SuppressLint("HandlerLeak")
    private final Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            mallAdapter.refresh();
        }
    };

    /**
     * 初始化数据
     * 使用okhttp3
     */
    private void initDatas() {

        OkHttpClient okHttpClient=new OkHttpClient();
        int displayCount = 6;
        Request request=new Request.Builder().url(BaseURL+"AndroidPractice/getlist?count="+ displayCount).method("GET",null).build();
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String data = response.body().string();
                Log.d("response",data);
                mDatas.addAll(ResolveJSON(data));
                handler.sendMessage(new Message());
            }
        });


    }

    /**
     * 解析后台传回的json文件
     * @param responseString json字符串
     * @return 当json res！=100时，返回空列表，==100时，返回包含数据的列表
     */
    private List<GoodsItem> ResolveJSON(String responseString){
        ArrayList<GoodsItem> goodsItems=new ArrayList<>();
        JSONObject jsonObject= JSON.parseObject(responseString);
        if(jsonObject.getIntValue("res")==0){
            return new ArrayList<>();
        }
       JSONArray dataArray= jsonObject.getJSONArray("data");
       for(int i=0;i<dataArray.size();i++){
           JSONObject jsonObject1=dataArray.getJSONObject(i);
            GoodsItem goodsItem=jsonObject1.toJavaObject(GoodsItem.class);
           String name= goodsItem.getName();
           name=name.split(" ")[0];
           if(name.length()>7){
               name=name.substring(0,7);
           }
           String url=BaseURL+"AndroidStatic/"+goodsItem.getPicture();
           goodsItem.setPicture(url);
           goodsItem.setName(name);
           goodsItems.add(goodsItem);
       }
       return goodsItems;
    }
}