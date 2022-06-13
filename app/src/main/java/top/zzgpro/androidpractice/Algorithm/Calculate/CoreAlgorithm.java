package top.zzgpro.androidpractice.Algorithm.Calculate;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CoreAlgorithm {

    private static final Stack<Double> st = new Stack<>();      //原始数字栈
    private static final Queue<Double> qu = new ArrayDeque<>();  //序列化数字队列,
    private static final Queue<String> queOp = new ArrayDeque<>();        //符号队列
    private static final Queue<String> newQueOp = new ArrayDeque<>();

    public static double calExp(String exp) {


//       String str="1+3*2-8/2+6";
//        String str="1+3*2/1";

        //本算法似乎不支持符号在前面,目前算法已经改进


        //处理运算符在式子最后
        if (exp.matches(".*[\\+\\-×÷]")) {
            exp = exp.substring(0, exp.length() - 1);
        }

        String[] sp; //存放分割数组

        //运算符在式子最前面
        if (exp.matches("[\\+\\-×÷].*")) {
            String fistElem=exp.substring(0,1); //截取首个字符
            exp=exp.substring(1);       //舍去首个字符
            //分割字符，提取数字
            sp = exp.split("\\+|-|×|÷");
            if(fistElem.equals("-")){       //首个字符为负号
                sp[0]="-"+sp[0];        //添加负号
            }
        }else{  //没有符号在前
            sp = exp.split("\\+|-|×|÷");
        }

        //之前直接分割字符会导致，数组第一位为空，导致程序无法运行

        for (int i = sp.length - 1; i >= 0; i--) {
            if (sp[i].equals(".")) {
                st.push(0.0);       //替换点号
            } else {
                st.push(Double.parseDouble(sp[i]));
            }

        }

        //寻找匹配字符串
        Pattern p = Pattern.compile("\\+|-|×|÷");
        Matcher m = p.matcher(exp);
        while (m.find()) {

            queOp.add(m.group());
        }

//        for(int i=sp.length-1;i>=0;i--){
//           System.out.println(st.pop());
//        }

//       int size=queNum.size();
//       for(int i=0;i<size;i++)
//           System.out.println(queNum.poll());

        //运算降级序列化
        while (st.size() > 0) {

            String currOp;

            if (queOp.size() > 0) {
                currOp = queOp.poll();
            } else {
                currOp = "0";
            }

            switch (currOp) {
                case "×":
                    st.push(st.pop() * st.pop());
                    break;
                case "÷":
                    st.push(st.pop() / st.pop());
                    break;
                case "+":
                    qu.add(st.pop());
                    newQueOp.add("+");
                    break;
                case "-":
                    qu.add(st.pop());
                    newQueOp.add("-");
                    break;
                default:
                    qu.add(st.pop());

            }
        }

        //正常运算
        if (qu.size() > 0) {
            double res = qu.poll();
            while (qu.size() > 0) {

                String op = "";
                if (newQueOp.size() > 0) {
                    op = newQueOp.poll();
                } else {
                    op = "none";
                }

                switch (op) {
                    case "+":
                        res += qu.poll();
                        break;
                    case "-":
                        res -= qu.poll();
                        break;
                    default:
                        System.out.println("none");
                }

            }

            return res;

        }

        return 0.0;
    }
}