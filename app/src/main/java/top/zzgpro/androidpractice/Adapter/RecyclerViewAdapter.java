package top.zzgpro.androidpractice.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import top.zzgpro.androidpractice.Item.RecyclerItemData;
import top.zzgpro.androidpractice.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<RecyclerItemData> mDatas = null;
    private LayoutInflater mInflater = null;

    public RecyclerViewAdapter(Context context, ArrayList<RecyclerItemData> datas) {
        this.mDatas = datas;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = this.mInflater.inflate(R.layout.mall_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecyclerItemData name = mDatas.get(position);
        holder.setPicture(name.getImage());
        holder.setTitle(name.getTitle());
        holder.setSellCount(name.getSellCount());

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
        }

        public void setSellCount(String text) {
            this.sellCount.setText(text);
        }

        public void setTitle(String title) {
            this.title.setText(title);
        }

        public void setPicture(int id) {
            this.picture.setImageResource(id);
        }

        public void setFreeget(Button freeget) {
            this.freeget = freeget;
        }

        private Button freeget;
    }
}

