package top.zzgpro.androidpractice.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import top.zzgpro.androidpractice.Item.BaseAdapterItem;
import top.zzgpro.androidpractice.R;


public class CustomBaseAdapter extends BaseAdapter {
    private Context context;
    private List<BaseAdapterItem> data;
    private int ResId;
    private LayoutInflater inflater ;

    /**
     *
     * @param context 上下文
     * @param data 数据列表
     * @param ResId item的资源id
     */
    public CustomBaseAdapter(Context context, List<BaseAdapterItem> data, int ResId){
        this.context=context;
        this.data=data;
        this.ResId=ResId;
        inflater= LayoutInflater.from(context);
    }
    public CustomBaseAdapter(){}
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public BaseAdapterItem getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView=null;
        if(view==null){
            itemView=inflater.inflate(this.ResId,null);
        }else{
            itemView=view;
        }
        ImageView iconImg = (ImageView) itemView.findViewById(R.id.icon_img);
        TextView titleTv = (TextView) itemView.findViewById(R.id.title_tv);
//        TextView infoTv = (TextView) itemView.findViewById(R.id.info_tv);
        Button subcribe=(Button)itemView.findViewById(R.id.subcribe);
        BaseAdapterItem data = getItem(i);
        if(null != data) {
            iconImg.setImageResource(data.getIcon());
            titleTv.setText(data.getTitle());
            subcribe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"您已订购："+data.getTitle(),Toast.LENGTH_SHORT).show();
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"您选择了："+data.getTitle(),Toast.LENGTH_SHORT).show();
                }
            });
        }

        return itemView;
    }
}
