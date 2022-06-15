package top.zzgpro.androidpractice.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import top.zzgpro.androidpractice.Bean.msgBean;
import top.zzgpro.androidpractice.R;
//视图适配器
public  class msgAdapter extends BaseAdapter {

    private List<msgBean> mData;   //数据源
    private LayoutInflater mInflater;   //布局资源

    public msgAdapter(Context context, List<msgBean> data)
    {
        this.mData = data;
        mInflater = LayoutInflater.from(context);
    }

    //构造函数
    public msgAdapter() {

    }

    //重写getCount方法
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mData.size(); //返回item个数
    }

    //重写getItem方法
    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mData.get(position); //返回相应位置item，即显示出第几个item
    }

    //重写getItemId方法
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position; //得到item的具体位置， 挺重要的  后面多次调用
    }

    @Override
    public int getItemViewType(int position)
    {
        msgBean bean = mData.get(position);
        return bean.getType();//返回布局文件的类型  判断是 接收者 还是 发送者
    }

    @Override
    public int getViewTypeCount(){
        return 2;        //类型的数目
    }

    //重写  视图   适配器核心部分
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder;  //ViewHolder对象

        //convertView -旧视图重用，如果可能的话。
        // 注意:在使用此视图之前，您应该检查该视图是否为非空且具有适当的类型。
        // 如果无法将此视图转换为显示正确的数据，则此方法可以创建一个新视图。
        // 异构列表可以指定视图类型的数量，因此该视图总是正确的类型
        // (参见getViewTypeCount()和getItemViewType(int))。
        if(convertView == null)
        {
            //Get the type of View that will be created by getView for the specified item.
            if(getItemViewType(position) == 0){
                holder = new ViewHolder();
                //对于 LayoutInflater 的 inflate() 方法，它的作用是把 xml 布局转换为对应的 View 对象
                convertView = mInflater.inflate(R.layout.layout_msg_receive, null);
                holder.icon = (ImageView) convertView.findViewById(R.id.receive_head_portrait);
                holder.text = (TextView) convertView.findViewById(R.id.receive_msg);

            }else {
                holder = new ViewHolder();
                //对于 LayoutInflater 的 inflate() 方法，它的作用是把 xml 布局转换为对应的 View 对象
                convertView = mInflater.inflate(R.layout.layout_msg_send, null);
                holder.icon = (ImageView) convertView.findViewById(R.id.send_head_portrait);
                holder.text = (TextView) convertView.findViewById(R.id.send_msg);
            }

            //View中的    setTag(Object)  表示给View添加一个格外的数据，以后可以用getTag()将这个数据取出来。
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag(); //如果有数据就将其getTag出来
        }
        //输出相应的控件  即图片和 消息
        holder.icon.setImageBitmap(mData.get(position).getIcon());
        holder.text.setText(mData.get(position).getText());
        return convertView;
    }

    //缓冲池
    public final class ViewHolder{
        public ImageView icon;
        public TextView text;
    }
}