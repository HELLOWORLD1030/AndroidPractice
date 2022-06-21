package top.zzgpro.androidpractice.Algorithm.Calculate;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CoreAlgorithm {
    public CoreAlgorithm(){}
    /**
     * 将表达式分割成运算数和运算符 并存入ArrayList中
     * @param exp 字符串类型的中缀表达式
     * @return Arraylist
     */
    public static ArrayList<String> GetListString(String exp){
        ArrayList<String> list=new ArrayList<>();
        StringBuilder builder=new StringBuilder();
        String[] sp=exp.split("");
        for (String s : sp) {
            if(s.matches("\\d")||".".equals(s)){
                builder.append(s);
            } else if(s.equals("√")){
                list.add(s);
            } else{
                list.add(builder.toString());
                list.add(s);
                builder.delete(0, builder.length());
            }
        }
        return list;
    }
    /**
     * 核心的计算程序 计算后缀表达式，获得答案
     * @param list 中缀表达式
     * @return 字符串类型的计算结果
     */
    public static String Calculate(List<String > list){
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
                Log.d("calculatestack",String.valueOf(num2));
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
        for (int i=0;i<ls.size();i++) {
            // 如果是一个数，加入s2
//            "\\d+"
            String item=ls.get(i);
            if (item.matches("(^-?[1-9]\\d*\\.\\d+$|^-?0\\.\\d+$|^-?[1-9]\\d*$|^0$)")) {
                s2.add(item);
            }  else if(item.equals("√")){
                String n=ls.get(++i);
                Log.d("calculatestack",n);
                s1.push(""+Math.sqrt(Double.parseDouble(n)));
            }

            // 如果s1为空,则直接将此运算符入栈；
            else if (s1.size() == 0) {
                s1.push(item);
            }

            // 如果是左括号“(”，则直接压入s1
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

    /**
     * 检查整个表达式是否合理
     * @param exp 字符串类型的表达式
     * @return 若合理返回true，不合理false
     */
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
}