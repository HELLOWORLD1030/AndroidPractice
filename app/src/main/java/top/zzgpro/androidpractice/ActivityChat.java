package top.zzgpro.androidpractice;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import top.zzgpro.androidpractice.Adapter.msgAdapter;
import top.zzgpro.androidpractice.Bean.msgBean;

public class ActivityChat extends Activity implements View.OnClickListener{

    private ListView mListView;

    //建立一个msgBean类型的动态列表  data对象
    List<msgBean> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mListView =findViewById(R.id.list_view);
        initMsg();
        Button button = findViewById(R.id.send1);
        button.setOnClickListener(this);
        mListView.setAdapter(new msgAdapter(this, data));
    }

    public void initMsg(){
        //创建一个msgBean  对象
        msgBean bean1 = new msgBean();
        bean1.setType(0);//左
        bean1.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.send));
        bean1.setText("你好呀");
        msgBean bean2 = new msgBean();
        bean2.setType(1);//右
        bean2.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.send));
        bean2.setText("你好");
        msgBean bean3 = new msgBean();
        bean3.setType(0);
        bean3.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.send));
        bean3.setText("很高兴为你服务");
        msgBean bean4 = new msgBean();
        bean4.setType(1);
        bean4.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.send));
        bean4.setText("好的，我知道了。");

        msgBean bean5 = new msgBean();
        bean5.setType(0);
        bean5.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.send));
        bean5.setText("希望能满足你的需求。");

        data.add(bean1);
        data.add(bean2);
        data.add(bean3);
        data.add(bean4);
        data.add(bean5);
    }
    @Override
    public void onClick(View v) {
        EditText editText = findViewById(R.id.input_text);
        String input = editText.getText().toString();
        if(input.equals("")){
            Toast.makeText(ActivityChat.this,"发送内容不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
            msgBean bean = new msgBean();
            bean.setType(1);
            bean.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.send));
            bean.setText(input);
            data.add(bean);
            mListView.setAdapter(new msgAdapter(this, data));
            mListView.setSelection(data.size());
            //清空输入框文本
            editText.setText("");

    }
}