package top.zzgpro.androidpractice.Adapter;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import top.zzgpro.androidpractice.Item.GoodsItem;
import top.zzgpro.androidpractice.Item.RecyclerItemData;
import top.zzgpro.androidpractice.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<GoodsItem> mDatas = null;
    private LayoutInflater mInflater = null;
    private Context context;
    public RecyclerViewAdapter(Context context, ArrayList<GoodsItem> datas) {
        this.mDatas = datas;
        this.mInflater = LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = this.mInflater.inflate(R.layout.mall_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GoodsItem name = mDatas.get(position);
        holder.setPicture(name.getPicture());
        holder.setTitle(name.getName());
        holder.setSellCount(name.getSellcount());

    }
    public void refresh(ArrayList<GoodsItem> list){
        //这个方法是我们自己手写的，主要是对适配器的一个刷新
        Log.d("networkRecyclerview","adapterdata"+String.valueOf(mDatas.size()));
//        this.mDatas.addAll(list);
        notifyDataSetChanged();
        Log.d("lcj","notifyDataSetChanged");
        Log.d("networkRecyclerview","adapterdata"+String.valueOf(mDatas.size()));
    }
    public void resetData(){
        this.mDatas.clear();
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView sellCount;
        private TextView title;
        private ImageView picture;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.freeget = (Button) itemView.findViewById(R.id.freeget);
            this.picture = (ImageView) itemView.findViewById(R.id.goodspicture);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.sellCount = (TextView) itemView.findViewById(R.id.sellcount);
            TextViewCompat.setAutoSizeTextTypeWithDefaults(this.title, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
            TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(this.title,10,20,1, TypedValue.COMPLEX_UNIT_SP);
        }

        public void setSellCount(String text) {
            this.sellCount.setText("已获 "+text+" 件");
        }

        public void setTitle(String title) {
            this.title.setText(title);
        }

        public void setPicture(String url) {
            Glide.with(context)
                    .load(url)
                    .placeholder(R.drawable.loading)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.drawable.error)
                    .into(this.picture);
        }

        public void setFreeget(Button freeget) {
            this.freeget = freeget;
        }

        private Button freeget;
    }
}

