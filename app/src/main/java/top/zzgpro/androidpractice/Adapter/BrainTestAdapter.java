
package top.zzgpro.androidpractice.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import top.zzgpro.androidpractice.Item.BrainTestItem;
import top.zzgpro.androidpractice.R;

public class BrainTestAdapter extends RecyclerView.Adapter<BrainTestAdapter.ViewHolder> {
    private final ArrayList<BrainTestItem> mDatas;
    private final LayoutInflater mInflater;
    private final Context context;
    public BrainTestAdapter(Context context, ArrayList<BrainTestItem> data){
        mInflater=LayoutInflater.from(context);
        mDatas=data;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=this.mInflater.inflate(R.layout.lab42_item,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setText(mDatas.get(position).getQuestion());
        holder.itemView.setOnClickListener(view -> Toast.makeText(context,mDatas.get(holder.getAdapterPosition()).getAnswer() ,Toast.LENGTH_SHORT).show());
    }
    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text=(TextView) itemView.findViewById(R.id.brainTest);
        }

        public void setText(String text) {
            this.text.setText(text);
        }
    }
}
