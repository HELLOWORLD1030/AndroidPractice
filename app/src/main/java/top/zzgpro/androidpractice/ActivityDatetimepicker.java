package top.zzgpro.androidpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.loper7.date_time_picker.DateTimeConfig;
import com.loper7.date_time_picker.DateTimePicker;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class ActivityDatetimepicker extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datetimepicker);
        DateTimePicker dateTimePicker=(DateTimePicker) findViewById(R.id.picker);
        //设置展示类型
        dateTimePicker.setDisplayType(new int[]{
                DateTimeConfig.YEAR,
                DateTimeConfig.MONTH,
                DateTimeConfig.DAY
        });
        //设置默认展示时间为当前时间
        dateTimePicker.setDefaultMillisecond(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());//defaultMillisecond 为毫秒时间戳
        dateTimePicker.setOnDateTimeChangedListener((mill)->{
            Log.d("mill",String.valueOf(mill));
            ChangeSelectShow(mill);
            return null;
        });
    }

    /**
     * 更改当前显示的时间
     * @param mill
     */
    private void ChangeSelectShow(Long mill){
        Instant instant=Instant.ofEpochMilli(mill);
        LocalDateTime dateTime=LocalDateTime.ofInstant(instant,ZoneOffset.of("+8"));
        String Year=String.valueOf( dateTime.getYear());
        String Month=String.valueOf( dateTime.getMonth().getValue());
        String Day=String.valueOf( dateTime.getDayOfMonth());
        EditText editText=(EditText) findViewById(R.id.selectdate);
        String tip=Year+"年"+Month+"月"+Day+"日";
        editText.setText(tip);

    }
}