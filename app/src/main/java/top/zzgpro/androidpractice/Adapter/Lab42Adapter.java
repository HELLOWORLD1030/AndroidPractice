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

import top.zzgpro.androidpractice.R;

public class Lab42Adapter extends RecyclerView.Adapter<Lab42Adapter.ViewHolder> {
    private ArrayList<String> mDatas = null;
    private LayoutInflater mInflater = null;
    private String[] Answer=null;
    private Context context;
    public Lab42Adapter(Context context, ArrayList<String> data, String[] answer){
        mInflater=LayoutInflater.from(context);
        mDatas=data;
        this.Answer=answer;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=this.mInflater.inflate(R.layout.lab42_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setText(mDatas.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,Answer[holder.getAdapterPosition()],Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text=(TextView) itemView.findViewById(R.id.brainTest);
        }

        public void setText(String text) {
            this.text.setText(text);
        }
    }
}
