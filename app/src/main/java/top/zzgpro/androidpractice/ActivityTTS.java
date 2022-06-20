package top.zzgpro.androidpractice;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class ActivityTTS extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private static final String TAG = "TTSActivity";

    private TextToSpeech textToSpeech;

    private EditText inputEt;

    private Button speechBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tts);
        speechBtn = findViewById(R.id.btn_speech);
        inputEt = findViewById(R.id.et_input);
        init();
    }

    private void init() {
        textToSpeech = new TextToSpeech(this, this);
        //设置语言
        int result = textToSpeech.setLanguage(Locale.ENGLISH);
        if (result != TextToSpeech.LANG_COUNTRY_AVAILABLE
                && result != TextToSpeech.LANG_AVAILABLE) {
            Toast.makeText(ActivityTTS.this, "TTS暂时不支持这种语音的朗读！",
                    Toast.LENGTH_SHORT).show();
        }
        //设置音调
        textToSpeech.setPitch(1.0f);
        //设置语速，1.0为正常语速
        textToSpeech.setSpeechRate(1.5f);
        //speech按钮监听事件
        speechBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //播放
                textToSpeech.speak(inputEt.getText().toString(),
                        TextToSpeech.QUEUE_ADD, null);
            }
        });
    }

    @Override
    public void onInit(int status) {
        //初始化成功
        if (status == TextToSpeech.SUCCESS) {
            Log.d(TAG, "init success");
        } else {
            Log.d(TAG, "init fail");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //中断当前话语
        textToSpeech.stop();
        //释放资源
        textToSpeech.shutdown();
    }
}
