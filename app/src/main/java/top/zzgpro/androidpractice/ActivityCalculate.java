package top.zzgpro.androidpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Stack;

import top.zzgpro.androidpractice.Algorithm.Calculate.CoreAlgorithm;

public class ActivityCalculate extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_result; // 声明一个文本视图对象
    private double result =0; // 当前的计算结果
    private String showText = ""; // 显示的文本内容
    private final StringBuilder builder=new StringBuilder();  //存储运算式子的字符串构造器
    private Stack<Double> NumberStack=new Stack<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        tv_result = findViewById(R.id.tv_result);

        int[] buttonSeq={
                R.id.btn_cancel, // “取消”按钮
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
                R.id.btn_reciprocal , // 求倒数按钮
                R.id.btn_zero , // 数字0
                R.id.btn_dot , // “小数点”按钮
                R.id.btn_equal  // “等号”按钮
//                R.id.ib_sqrt // “开平方”按钮
        };

        for(int buttonId:buttonSeq)
            findViewById(buttonId).setOnClickListener(this);

//        builder.append(0);
    }

//符号分类
    //数字
    //四则运算符
    //小数点
    //根号，倒数，等号，直接出结果
    //CE C

/*
    @Override
    public void onClick(View v) {
        int id=v.getId();
        String inputText="";

//        if(result!=0){          //如果结果不为0，则按照结果进行运算
//            clear();
//            builder.append(result);
//        }


        //若不为根号，因为根号按钮无法取值
//        if(id!=R.id.ib_sqrt) {
            inputText = ((TextView) v).getText().toString();
        Log.d("calculate",inputText);
            //判断是否为数字、小数点以及四则运算符
            if (inputText.matches("\\d|\\.")) {         //输入是否为数字或点号
                resultCheck();
                if(builder.toString().equals("0")){
                    builder.deleteCharAt(builder.length()-1);}

                builder.append(inputText);
                if(verifyExp(builder.toString())){
                    refreshText(builder.toString());      //表达式正确刷新
                }else{
                    builder.deleteCharAt(builder.length() - 1);  //表达式不正确删除最后一位字符
                }


            } else if (inputText.matches("\\+|-|×|÷")) {        //输入为四则运算符

                resultCheck();

                builder.append(inputText);
                if(verifyExp(builder.toString())){
                    refreshText(builder.toString());
                }else{                                               //更替运算符操作
                    builder.deleteCharAt(builder.length() - 1);
                    builder.deleteCharAt(builder.length() - 1);
                    builder.append(inputText);
                    refreshText(builder.toString());
                }

            }
            else {                          //点击了CE C 1/X =

                switch (inputText) {
                    case "CE":
                        resultCheck();
                        //有字符才能删除
                        if (builder.length() > 0) {
                            builder.deleteCharAt(builder.length() - 1);
                            refreshText(builder.toString());
                        } else {

                            Toast.makeText(this, "没有数字可删了", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case "C":
                        refreshText("");
                        result=0.0;
                        builder.delete(0, builder.length());    //builder清空
                        builder.append(0);
                        break;
                    case "1/x":
                        resultCheck();
                        result=1/(CoreAlgorithm.calExp(builder.toString()));
                        refreshText("1/("+builder.toString()+")=\n"+result);
                        break;
                    case "＝":
                        resultCheck();
                        if(result==0.0) {
                            result = CoreAlgorithm.calExp(builder.toString());
//                                builder.append("=");//容易出错 ,按等号会把这个式子进行运算
                            refreshText(builder.toString() + "=\n" + result);
                        }
                        break;
                    default:
                        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
                }

            }
//        }else{      // 点击了开根号,由于算法不支持有运算符在前,目前已经支持
//            resultCheck();  //经过一次检查结果被清零，结果存于builder中
//            result = Math.sqrt(CoreAlgorithm.calExp(builder.toString()));
//            refreshText("√(" + builder.toString() + ")=\n" + result);
//
//        }//根号逻辑

    }
    */

    //检查整个表达式
    public boolean verifyExp(String exp){       //验证整个表达式是否合法
        String lastNum="";
        String[] sp=exp.split("\\+|-|×|÷");      //将操作数分割出来
        char lastChar=exp.charAt(exp.length()-1);       //获得最后一个字符

        lastNum=sp[sp.length-1];     //取得最后一位操作数

        if(String.valueOf(lastChar).matches("\\+|-|×|÷"))  //如果当前符号为四则运算符
        {
            lastNum="";
            return exp.matches(".*(\\d[+-×÷])|.*(\\.[+-×÷])");      //验证最后的运算符是否符合只有一个原则

        }else{                                                          //最后一位为运算数
            return  lastNum.matches("^[-]?\\d*\\.?\\d*");   //验证最后一位运算数是否合法

        }
    }


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

    //基本每个出结果的按钮都要执行一次这个函数
    public void resultCheck(){      //运算结果检查，有结果用结果，结果不为数字进行处理
        if(result!=0){          //如果结果不为0，则按照结果进行运算
            String res=String.valueOf(result);
            if(res.matches("^[-]?\\d*\\.?\\d*")){   //若为浮点数字
                clear();
                builder.append(result);
                result=0;    //结果不清零，检查的时候就会一直重复放入结果
            }else{      //若结果为字母，分母为0会返回Infinity，以及负数开方
                clear();
                builder.append("0");
                result=0;
            }
        }

        if(builder.length()==0){
            builder.append(0);
        }

    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        String inputText="";
        inputText = ((TextView) view).getText().toString();
        Log.d("cal",inputText);
        if(!"＝".equals(inputText)){
            builder.append(inputText);
        }else{
//            String[] sp=builder.toString().split("\\+|-|×|÷");
//            for (String s : sp) {
//                Log.d("cal",s);
//            }
            Stack<String> stack=GetListString(builder.toString());
            for (String s : stack) {
                Log.d("cal",s);
            }
        }
    }
    private Stack<String> GetListString(String exp){
        Stack<String> stack=new Stack<>();
        StringBuilder builder=new StringBuilder();
        String[] sp=exp.split("");
        for (String s : sp) {
            if(s.matches("\\d")||".".equals(s)){
                builder.append(s);
            }else{
                stack.push(builder.toString());
                stack.push(s);
                builder.delete(0, builder.length());
            }
        }
        return stack;
    }
}