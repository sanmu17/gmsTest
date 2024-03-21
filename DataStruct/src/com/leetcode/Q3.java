package com.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Q3 {

    public static int lengthOfLongestSubstring(String s) {
        char a[] = s.toCharArray();
        int len = 0, k = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = k; j < i; j++) {
                if (a[i] == a[j]) {
                    k = j + 1;
                    break;
                }
            }
            len = Math.max(len, i - k + 1);
        }
        return len;
    }

    public static void main(String[] args) {
//        Executors
//        new HashMap<>();
        new HashMap<>();
        char[] nums = new char[10];
        Long.parseLong(nums.toString());
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }
}
