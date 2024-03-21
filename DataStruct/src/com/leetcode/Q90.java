package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.pow;

public class Q90 {

    static class Solution {
        List<Integer> t = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> subsetsWithDup(int[] nums) {
            Arrays.sort(nums);
            getSub(nums, 0, false);
            return res;
        }

        private void getSub(int[] nums, int i, boolean flag) {
            if (i == nums.length) {
                res.add(new ArrayList<Integer>(t));
                return;
            }
            getSub(nums, i + 1, false);
            if (!flag && i > 0 && nums[i - 1] == nums[i]) {
                return;
            }
            t.add(nums[i]);
            getSub(nums, i + 1, true);
            t.remove(t.size() - 1);
        }

        public int reverse(int x) {
            String s = String.valueOf(x);
            int l = s.length();
            StringBuilder stringBuilder = new StringBuilder();
            int start = 0;
            if (s.charAt(0) == '-') start = 1;
            for (int i = l - 1; i >= start; i--) {
                stringBuilder.append(s.charAt(i));
            }
            long num = Long.parseLong(stringBuilder.toString());
            if (num >= pow(2, 31) - 1 || num <= -(pow(2, 31))) {
                num = 0l;
            }
            return (int) num;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{1, 2, 2};
        List<List<Integer>> lists = solution.subsetsWithDup(nums);
        for (List<Integer> list : lists) {
            for (Integer i : list) {
                System.out.print(i);
            }
            System.out.println();
        }
        System.out.println(solution.reverse(123));
    }
}
