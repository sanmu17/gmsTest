package com.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Stack_infixToSuffix {

    public static void main(String[] args) {
        String s = infixToSuffix("30+ 50 - (4 * ( 4 + 50 )) ");
//        getList("3 + 5 - 4 * ( 4 + 5 ) ");
        System.out.println("后缀表达式："+s);

        int calculate = calculate(s);
        System.out.println("运算结果："+calculate);
    }

    public static List<String> getList(String s){
//        String[] se = s.split(" ");
        List<String> list = new ArrayList<>();
//        for (String s1 : se) {
//            list.add(s1);
//        }
        s=s.replace(" ","");
        String s1="";
        for (int i = 0; i < s.length(); i++) {
            String t = String.valueOf(s.charAt(i));
            if(getPriority(t)==0){
                s1 = s1 + t;
                continue;
            }else {
                if (!s1.equals("")) {
                    s1=s1.trim();
                    list.add(s1);
                }
                list.add(t.trim());
                s1="";
            }
        }
        return list;
    }

    public static String infixToSuffix(String s){
        //"3+4-5*8"--->" 3 4 + 5 8 * - "
        //将3和4压入数字栈再压入+
        // 将-压入符号栈，将5压入数字栈
        // *优先级比-高压入符号栈 再将8压入数字栈
        // 后面没有数据就将数字栈中数字依次压入符号栈，
        // 再将符号栈中数据依次弹出即为后缀表达式
        //"3 + 5 - 4 * ( 4 + 5 ) " -72 转为后缀表达式：3 5 + 4 5 + 4 * -,
//        String[] se = s.split(" ");
//        List<String> list = new ArrayList<>();
//        for (String s1 : se) {
//            list.add(s1);
//        }

        List<String> list = getList(s);
        System.out.println("====="+list);
        String result = "";

      Stack<String> numStack = new Stack<>();
        Stack<String> operStack = new Stack<>();
        for (String s1 : list) {
            if(s1.matches("\\d+")){
                numStack.push(s1);
            }else{
                if(operStack.isEmpty()){
                    operStack.push(s1);
                }else {
                    String peek = operStack.peek();
                    if(getPriority(peek) < getPriority(s1)){
                        operStack.push(s1);
//                        operStack.push(numStack.pop());
                    }else{
                        String t = operStack.pop();
                        numStack.push(t);
                        operStack.push(s1);
                    }
                }
            }
        }
        while(!numStack.isEmpty()){
            String pop = numStack.pop();
            operStack.push(pop);
        }
        while (!operStack.isEmpty()){
            String s2 = operStack.pop();
            if(s2.equals("(")||s2.equals(")")){
                continue;
            }
            result = result + s2 +" ";
        }
        return result;
    }

    static int getPriority(String s){
        int p = 0;
        if(s.equals("+")||s.equals("-")) {
            p = 1;
        }else if(s.equals("*")||s.equals("/")){
            p = 2;
        }else if(s.equals("(")||s.equals(")")){
            p = 3;
        }else {
            p = 0;
        }
        return p;
    }



    public static int calculate(String suffix){
        String[] se = suffix.split(" ");
        List<String> list = new ArrayList<>();
        for (String s1 : se) {
            list.add(s1);
        }
        Stack<String> stack = new Stack<>();
        for (String s : list) {
            if(s.matches("\\d+")){//匹配到数字入栈
                stack.push(s);
            }else{
                //匹配到了操作符 进行计算
                int n1 = Integer.parseInt(stack.pop());
                int n2 = Integer.parseInt(stack.pop());
                int res = 0;
                if(s.equals("+")){
                    res = n1+n2;
                    stack.push(String.valueOf(res));
                }
                else if(s.equals("-")){
                    res = n2-n1;
                    stack.push(String.valueOf(res));
                }
                else if(s.equals("*")){
                    res = n1*n2;
                    stack.push(String.valueOf(res));
                }
                else if(s.equals("/")){
                    res = n2/n1;
                    stack.push(String.valueOf(res));
                }else{
                    throw new RuntimeException("运算符异常");
                }
            }
        }
        int res = Integer.parseInt(stack.pop());
        return res;
    }
}
