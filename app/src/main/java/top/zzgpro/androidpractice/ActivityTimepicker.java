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

public class ActivityTimepicker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timepicker);
        DateTimePicker dateTimePicker=(DateTimePicker) findViewById(R.id.picker);
//        dateTimePicker.setOnDateTimeChangedListener((mill)->{
//            Log.d("datetimepick",String.valueOf(mill));
//        });
        dateTimePicker.setDisplayType(new int[]{
                DateTimeConfig.HOUR,
                DateTimeConfig.MIN,
                DateTimeConfig.SECOND
        });
        dateTimePicker.setDefaultMillisecond(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());//defaultMillisecond 为毫秒时间戳
        dateTimePicker.setOnDateTimeChangedListener((mill)->{
            Log.d("mill",String.valueOf(mill));
            ChangeSelectShow(mill);
            return null;
        });
    }
    private void ChangeSelectShow(Long mill){
        Instant instant=Instant.ofEpochMilli(mill);
        LocalDateTime dateTime=LocalDateTime.ofInstant(instant,ZoneOffset.of("+8"));
        String Hour=String.valueOf( dateTime.getHour());
        String Mininte=String.valueOf( dateTime.getMinute());
        String Second=String.valueOf( dateTime.getSecond());
        EditText editText=(EditText) findViewById(R.id.selectdate);
        String tip=Hour+"时"+Mininte+"分"+Second+"秒";
        editText.setText(tip);

    }
}