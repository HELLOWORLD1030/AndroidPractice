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