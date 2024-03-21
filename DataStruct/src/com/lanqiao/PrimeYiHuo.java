package com.lanqiao;

import java.util.Scanner;

/**
 * 蓝桥8082 质数异或
 * 问题描述
 * 小蓝最近在学习位运算知识，这天她在做练习的时候碰到了一个非常有意思的题目，题意如下:
 * 现在有一个正整数n，请你计算以下有多少个整数y满足:0≤y≤n且ny=p，其中p为质数，表示异或运算。
 * 小蓝觉得这个题目非常有意思，但是小蓝对于位运算的性质运用还不熟练请你帮她解决这个问题。
 * 输入格式
 * 输入一行一个整数，代表几。
 * 输出格式
 * 输出一行一个整数，代表满足条件的 y的数目。
 * 样例输入
 * 5
 * 样例输出
 * 2
 * 说明
 * 对于样例，5=(101)2，当y=2时5y=7，当y=0时5 y=5 。所有y的值有两种。
 */
public class PrimeYiHuo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int k = n ^ i;
            if (isPrime(k)) {
                sum++;
            }
        }
        System.out.println(sum);
    }

    private static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        int sqrt = (int) Math.sqrt(n) + 1;
        for (int i = 2; i < sqrt; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
