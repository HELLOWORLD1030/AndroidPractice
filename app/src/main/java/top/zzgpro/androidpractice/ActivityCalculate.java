package top.zzgpro.androidpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.TextView;

import java.util.ArrayList;

import java.util.List;
import java.util.Stack;

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
//            R.id.btn_reciprocal , // 求倒数按钮
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
        for(int buttonId:buttonSeq)
            findViewById(buttonId).setOnClickListener(this);
    }


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
        String lastNum;
        String[] sp=exp.split("[+\\-*/]");      //将操作数分割出来
        char lastChar=exp.charAt(exp.length()-1);       //获得最后一个字符

        lastNum=sp[sp.length-1];     //取得最后一位操作数

        if(String.valueOf(lastChar).matches("[+\\-*/]"))  //如果当前符号为四则运算符
        {
            return exp.matches(".*(\\d[+\\-*/])|.*(\\.[+\\-*/])");      //验证最后的运算符是否符合只有一个原则

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
    private ArrayList<String> GetListString(String exp){
        ArrayList<String> list=new ArrayList<>();
        StringBuilder builder=new StringBuilder();
        String[] sp=exp.split("");
        for (String s : sp) {
            if(s.matches("\\d")||".".equals(s)){
                builder.append(s);
            }else{
                list.add(builder.toString());
                list.add(s);
                builder.delete(0, builder.length());
            }
        }
        return list;
    }
    private String Calculate(List<String > list){
        Stack<String> stack = new Stack<>();
        // 遍历 ls
        for (String item : list) {
            // 这里使用正则表达式来取出数
            if (item.matches("(^-?[1-9]\\d*\\.\\d+$|^-?0\\.\\d+$|^-?[1-9]\\d*$|^0$)")) { // 匹配的是多位数
                // 入栈
                stack.push(item);
            } else {
                // pop出两个数，并运算， 再入栈
//                int num2 = Integer.parseInt(stack.pop());
//                int num1 = Integer.parseInt(stack.pop());
                double num2 = Double.parseDouble(stack.pop());
                double num1 = Double.parseDouble(stack.pop());
                double res;
                switch (item) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num1 - num2;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num1 / num2;
                        break;
                    default:
                        throw new RuntimeException("运算符有误");
                }
                // 把res 入栈
                stack.push("" + res);
            }
        }
        // 最后留在stack中的数据是运算结果
        return String.valueOf(Double.parseDouble(stack.pop())) ;

    }
    /**
     * 中缀表达式List转换为后缀表达式List
     * @param ls 中缀表达式的List
     *
     * @return 后缀表达式的List
     */
    public static List<String> parseSuffixExpreesionList(List<String> ls) {
        // 定义两个栈
        Stack<String> s1 = new Stack<>(); // 符号栈
        // 说明：因为s2 这个栈，在整个转换过程中，没有pop操作，而且后面我们还需要逆序输出
        // 因此比较麻烦，这里我们就不用 Stack<String> 直接使用 List<String> s2
        // Stack<String> s2 = new Stack<String>(); // 储存中间结果的栈s2
        List<String> s2 = new ArrayList<>(); // 储存中间结果的Lists2
        // 遍历ls
        for (String item : ls) {
            // 如果是一个数，加入s2
//            "\\d+"
            if (item.matches("(^-?[1-9]\\d*\\.\\d+$|^-?0\\.\\d+$|^-?[1-9]\\d*$|^0$)")) {
                s2.add(item);
            } // 如果s1为空,则直接将此运算符入栈；
            else if (s1.size() == 0) {
                s1.push(item);
            } // 如果是左括号“(”，则直接压入s1
            else if (item.equals("(")) {
                s1.push(item);
                // 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
            } else if (item.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();// !!! 将 ( 弹出 s1栈， 消除小括号
            } //栈顶运算符为左括号“(”，则直接将此运算符入栈；
            else if (s1.peek().equals("(")) {
                s1.push(item);
            } else {
                // 当item的优先级小于等于s1栈顶运算符, 将s1栈顶的运算符弹出并加入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较				// 问题：我们缺少一个比较优先级高低的方法
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                // 否则，若优先级比栈顶运算符的高，也将运算符压入s1；
                // 还需要将item压入栈
                s1.push(item);
            }
        }
        // 将s1中剩余的运算符依次弹出并加入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2; // 注意因为是存放到List, 因此按顺序输出就是对应的后缀表达式对应的List
    }
    private void ClickEqualTag(){
        if(builder.length()<1||clearFlag){
            return;
        }
        builder.append("=");
        Log.d("cal",builder.toString());
        ArrayList<String> stack=GetListString(builder.toString());
        stack.remove(stack.size()-1);
        List<String> ss= parseSuffixExpreesionList(stack);

        String result= Calculate(ss);
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
        if(verifyExp(builder.toString())){
            refreshText(builder.toString());      //表达式正确刷新
        }else{
            builder.deleteCharAt(builder.length() - 1);  //表达式不正确删除最后一位字符
        }
    }
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
                R.id.btn_plus
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
class Operation {
    private final static int ADD = 1;
    private final static int SUB = 1;
    private final static int MUL = 2;
    private final static int DIV = 2;

    // 写一个方法，返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符" + operation);
                break;
        }
        return result;
    }

}

