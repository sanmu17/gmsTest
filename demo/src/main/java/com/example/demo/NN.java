package com.example.demo;

/**
 * 类描述：
 *
 * @ClassName NN
 * @Description TODO
 * @Author 1537414333@qq.com
 * @Date 2021/9/16 20:08
 * @Version 1.0
 */
public class NN {
    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < i + 1; j++) {
                System.out.print(j + "*" + i + "=" + i * j + "\t");
            }
            System.out.println();
        }
    }
}
