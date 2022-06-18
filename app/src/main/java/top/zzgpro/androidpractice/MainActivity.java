package top.zzgpro.androidpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.xuexiang.xui.XUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        XUI.initTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View view = this.getWindow().getDecorView();
        EnableButton(R.id.calculater,ActivityCalculate.class);
        EnableButton(R.id.scroll,ActivityBallview.class);
        EnableButton(R.id.musicplayer,MusicPlayer.class);
        EnableButton(R.id.brainTest,lab42.class);
        EnableButton(R.id.listview,ActivityBaselistview.class);
        EnableButton(R.id.datePicker,ActivityDatetimepicker.class);
        EnableButton(R.id.timePicker,ActivityTimepicker.class);
        EnableButton(R.id.chat,ActivityChat.class);
        EnableButton(R.id.mall,ActivityMall.class);
    }
    private void EnableButton(int buttonId,Class destClass){
        Button button = findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,destClass);
                startActivity(intent);
            }
        });
    }
    private ArrayList<View> getAllButton(View view){

        ArrayList<View> allchildren = new ArrayList<View>();
        if (view instanceof ViewGroup) {
            ViewGroup vp = (ViewGroup) view;

            for (int i = 0; i < vp.getChildCount(); i++) {
                View viewchild = vp.getChildAt(i);
                Log.d("button",viewchild.getClass().getSimpleName()+" "+i);
                if(viewchild.getClass().getSimpleName().contains("Button")){
                    allchildren.add(viewchild);
                    Log.d("button",String.valueOf(viewchild.getId()));
                }
                getAllButton(viewchild);
            }
        }
        return allchildren;
    }
}