package top.zzgpro.androidpractice;

import static top.zzgpro.androidpractice.Algorithm.Calculate.CoreAlgorithm.Calculate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewCompat;

import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import android.widget.TextView;

import java.util.ArrayList;

import java.util.List;
import java.util.Stack;

import top.zzgpro.androidpractice.Algorithm.Calculate.CoreAlgorithm;

public class ActivityCalculate extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_result; // 声明一个文本视图对象
    private String showText = ""; // 显示的文本内容
    private boolean clearFlag=false;
    private TextView texCount;
    private final StringBuilder builder=new StringBuilder();  //存储运算式子的字符串构造器
    private final int[] buttonSeq={
            R.id.btn_del, // “取消”按钮
            R.id.btn_divide,// “除法”按钮
            R.id.btn_multiply , // “乘法”按钮
            R.id.btn_clear , // “清除”按钮
            R.id.btn_seven , // 数字7
            R.id.btn_eight , // 数字8
            R.id.btn_nine , // 数字9
            R.id.btn_plus , // “加法”按钮
            R.id.btn_four , // 数字4
            R.id.btn_five , // 数字5
            R.id.btn_six , // 数字6
            R.id.btn_minus , // “减法”按钮
            R.id.btn_one , // 数字1
            R.id.btn_two , // 数字2
            R.id.btn_three , // 数字3
            R.id.btn_sqrt , // 求倒数按钮
            R.id.btn_zero , // 数字0
            R.id.btn_dot , // “小数点”按钮
            R.id.btn_equal  // “等号”按钮
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_calculate_new);
        tv_result = findViewById(R.id.tv_result);
        texCount=findViewById(R.id.tex_count);
        TextViewCompat.setAutoSizeTextTypeWithDefaults(tv_result, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(tv_result,10,35,1, TypedValue.COMPLEX_UNIT_SP);
        TextViewCompat.setAutoSizeTextTypeWithDefaults(texCount, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(texCount,10,35,1, TypedValue.COMPLEX_UNIT_SP);

        for(int buttonId:buttonSeq)
            findViewById(buttonId).setOnClickListener(this);
    }


    //检查整个表达式

    // 刷新文本显示
    private void refreshText(String text) {
        showText = text;
        tv_result.setText(showText);
    }
    // 清空并初始化
    private void clear() {
        builder.delete(0, builder.length());
        showText="";
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        String inputText;
        inputText = ((TextView) view).getText().toString();
        ButtonType type=GetClickedButtonType(id);
        Log.d("cal",type.toString());
        switch(type){
            case EQUAL:
                ClickEqualTag();
                break;
            case opNumber:
            case opCode:
                ClickNumberTag(inputText);
                break;
            case CE:
                if (builder.length() > 0) {
                    builder.deleteCharAt(builder.length() - 1);
                    refreshText(builder.toString());
                }
                break;
            case CANCEL:
                clearFlag=false;
                refreshText("");
                builder.delete(0, builder.length());    //builder清空
                break;
        }
    }
    private void ClickEqualTag(){
        if(builder.length()<1||clearFlag){
            return;
        }
        builder.append("=");
        Log.d("cal",builder.toString());
        ArrayList<String> stack= CoreAlgorithm.GetListString(builder.toString());
        stack.remove(stack.size()-1);
        List<String> ss= CoreAlgorithm.parseSuffixExpreesionList(stack);

        String result= CoreAlgorithm.Calculate(ss);
        Log.d("calr",result);
        builder.append(result);
        refreshText(builder.toString());
        clearFlag=true;
//        builder.delete(0,builder.length());
//        refreshText("");
    }
    private void ClickNumberTag(String inputText){
        if(clearFlag==true){
            texCount.setText(builder.toString());
            clear();
            clearFlag=false;
        }
        builder.append(inputText);
//        if(verifyExp(builder.toString())){
            refreshText(builder.toString());      //表达式正确刷新
//        }else{
//            builder.deleteCharAt(builder.length() - 1);  //表达式不正确删除最后一位字符
//        }
    }

    /**
     * 依据传入的组件ID，返回按键类型
     * @param id
     * @return enum ButtonType
     *
     */
    private ButtonType GetClickedButtonType(int id){
        int[] NumberButton={R.id.btn_zero , // 数字0
                R.id.btn_dot , // “小数点”按钮
                R.id.btn_one , // 数字1
                R.id.btn_two , // 数字2
                R.id.btn_three , // 数字3//
                R.id.btn_seven , // 数字7
                R.id.btn_eight , // 数字8
                R.id.btn_nine , // 数字9
                R.id.btn_four , // 数字4
                R.id.btn_five , // 数字5
                R.id.btn_six , // 数字6//
                 };
        int[] OPCodeButton={
                R.id.btn_minus , // “减法”按钮
                R.id.btn_multiply , // “乘法”按钮
                R.id.btn_divide,// “除法”按钮
                R.id.btn_plus,
                R.id.btn_sqrt
        };
        if(id==R.id.btn_del)
            return ButtonType.CE;
        if(id==R.id.btn_equal)
            return ButtonType.EQUAL;
        if(id==R.id.btn_clear)
            return ButtonType.CANCEL;
        for (int k : NumberButton) {
            if (k == id)
                return ButtonType.opNumber;
        }
        for (int j : OPCodeButton) {
            if (j == id)
                return ButtonType.opCode;
        }
        return ButtonType.UnKnown;
    }
    enum ButtonType
    {
        opNumber,opCode,CE,EQUAL,CANCEL,UnKnown
    }
}


