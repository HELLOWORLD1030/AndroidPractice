package top.zzgpro.androidpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.xuexiang.xui.XUI;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        XUI.initTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EnableButton(R.id.calculater,ActivityCalculate.class);
        EnableButton(R.id.scroll,ActivityBallview.class);
        EnableButton(R.id.musicplayer,MusicPlayer.class);
        EnableButton(R.id.brainTest, ActivityBrainTest.class);
        EnableButton(R.id.listview,ActivityBaselistview.class);
        EnableButton(R.id.datePicker,ActivityDatetimepicker.class);
        EnableButton(R.id.timePicker,ActivityTimepicker.class);
        EnableButton(R.id.chat,ActivityChat.class);
        EnableButton(R.id.mall,ActivityMall.class);
//        EnableButton(R.id.tts,ActivityTTS.class);
        EnableButton(R.id.scancode,ActivityScan.class);
        EnableButton(R.id.sensor,ActivitySensor.class);
        EnableButton(R.id.accelerate,ActivityAccelerate.class);
    }

    /**
     * 配置主页面的按钮
     * @param buttonId 按钮ID
     * @param destClass 对应的Java类class
     */
    private void EnableButton(int buttonId,Class destClass){
        Button button = findViewById(buttonId);
        button.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this,destClass);
            startActivity(intent);
        });
    }

}