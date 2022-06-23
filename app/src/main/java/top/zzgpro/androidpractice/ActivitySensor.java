package top.zzgpro.androidpractice;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ActivitySensor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        ListView listView= findViewById(R.id.sensorlist);
        //获取系统传感器管理器
        SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //通过系统传感器管理器..获取本机所有传感器.
        List<Sensor> sensorList = sm.getSensorList(Sensor.TYPE_ALL);
        listView.setAdapter(new ArrayAdapter<>(this,R.layout.array_item,sensorList));
        for(Sensor s :sensorList){
            Log.i("sensorList",s.toString());
        }
    }
}