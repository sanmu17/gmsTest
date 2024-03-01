package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类描述：leetcode刷题 各种题解
 *
 * @ClassName Solution
 * @Description TODO
 * @Author 1537414333@qq.com
 * @Date 2022-06-10 19:08
 * @Version 1.0
 */
public class Solution {
    /**
     * 50 Pow(x,n)
     */
    public double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? fastPow(x, N) : 1.0 / fastPow(x, -N);
    }

    private double fastPow(double x, long i) {
        if (i == 0) {
            return 1.0;
        }
        double y = fastPow(x, i / 2);
        return i % 2 == 1 ? y * y * x : y * y;
    }

    /**
     * 子集
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null) {
            return res;
        }
        generate(res, 0, nums, new ArrayList<Integer>());
        return res;
    }

    private void generate(List<List<Integer>> res, int i, int[] nums, ArrayList<Integer> ans) {
        if (i == nums.length) {
            res.add(new ArrayList<Integer>(ans));
            return;
        }

        generate(res, i + 1, nums, ans);

        ans.add(nums[i]);
        generate(res, i + 1, nums, ans);

        ans.remove(ans.size() - 1);
    }

    /**
     * 17、电话号码的字母组合
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     * <p>
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/letter-combinations-of-a-phone-number
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        // 排列组合 2-abc 3-def ....
        // abc def ghi jkl mno pqr stu wxyz 2-9
        List<String> ans = new ArrayList<String>();
        if (digits.length() == 0 || digits == null) {
            return ans;
        }
        Map<Character, String> map = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        generateLetter(digits, ans, 0, map, "");
        return ans;
    }

    private void generateLetter(String digits, List<String> ans, int i, Map<Character, String> map, String s) {
        if (i == digits.length()) {
            ans.add(s);
            return;
        }
        char c = digits.charAt(i);
        String letter = map.get(c);
        for (int j = 0; j < letter.length(); j++) {
            generateLetter(digits, ans, i + 1, map, s + letter.charAt(j));
        }
    }


    /**
     * 4. 寻找两个正序数组的中位数
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLen = nums1.length + nums2.length;
        int k = totalLen / 2;
        if (totalLen % 2 == 1) {
            return (double) getKthNum(nums1, nums2, k + 1);
        } else {
            return (double) (getKthNum(nums1, nums2, k + 1) + getKthNum(nums1, nums2, k + 2)) / 2;
        }
    }

    private int getKthNum(int[] nums1, int[] nums2, int i) {
        int len1 = nums1.length, len2 = nums2.length;
        int index1 = 0, index2 = 0;
        while (true) {
            if (len1 == index1) {
                return nums2[i + index2 - 1];
            }
            if (len2 == index2) {
                return nums1[i + index1 - 1];
            }
            if (i == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }
            int half = i / 2;
            int new1 = Math.min(len1, index1 + half) - 1;
            int new2 = Math.min(len2, index2 + half) - 1;
            int p1 = nums1[new1], p2 = nums2[new2];
            if (p1 <= p2) {
                i = i - (new1 - index1 + 1);
                index1 = new1 + 1;
            } else {
                i = i - (new2 - index2 + 1);
                index2 = new2 + 1;
            }
        }
    }

    /**
     * 74. 搜索二维矩阵
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int len = m * n;
        int low = 0, hi = len - 1;
        int mid = 0;
        while (low <= hi) {
            mid = low + (hi - low) / 2;
            System.out.println(matrix[mid / n][mid % n]);
            if (matrix[mid / n][mid % n] >= target) {
                hi = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        System.out.println("===================");
        System.out.println(low);
        System.out.println("最后坐标[" + (low / n) + "][" + (low % n) + "]");

        int x = low / n, y = low % n;
        if (x >= m) {
            return false;
        }
        return matrix[low / n][low % n] == target;
    }

    /**
     * 62. 不同路径
     * 顺序结构：从初始节点到末尾节点
     * 逆序结构：从终点到初始节点
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] res = new int[m][n];
        for (int i = 1; i < m; i++) {
            res[i][0] = 1;
        }
        for (int i = 1; i < n; i++) {
            res[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                res[i][j] = res[i - 1][j] + res[i][j - 1];
            }
        }
        return res[m - 1][n - 1];
    }

    /**
     * 63. 不同路径 II
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] res = new int[m][n];
        res[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1;
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                res[i][0] = 0;
            } else {
                res[i][0] = res[i - 1][0];
            }
        }
        for (int i = 1; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                res[0][i] = 0;
            } else {
                res[0][i] = res[0][i - 1];
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    res[i][j] = 0;
                } else {
                    res[i][j] = res[i - 1][j] + res[i][j - 1];
                }
            }
        }
        return res[m - 1][n - 1];
    }

    /**
     * 1143. 最长公共子序列
     * *
     *
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] res = new int[m][n];
        for (int i = 1; i < m; i++) {
            char x = text1.charAt(i - 1);
            for (int j = 1; j < n; j++) {
                char y = text2.charAt(j - 1);
                if (x == y) {
                    res[i][j] = res[i - 1][j - 1] + 1;
                } else {
                    res[i][j] = Math.max(res[i - 1][j], res[i][j - 1]);
                }
            }
        }
//        res[0][0] = text1.charAt(0) == text2.charAt(0) ? 1 : 0;
//        for (int i = 1; i < m; i++) {
//            if (text1.charAt(i) == text2.charAt(0)) {
//                res[i][0] =  1;
//            } else {
//                res[i][0] = res[i - 1][0];
//            }
//        }
//        for (int i = 1; i < n; i++) {
//            if (text1.charAt(0) == text2.charAt(i)) {
//                res[0][i] = 1;
//            } else {
//                res[0][i] = res[0][i - 1];
//            }
//        }
//        for (int i = 1; i < m; i++) {
//            for (int j = 1; j < n; j++) {
//                if (text1.charAt(i) == text2.charAt(j)) {
//                    res[i][j] = res[i-1][j - 1] + 1;
//                } else {
//                    res[i][j] = Math.max(res[i - 1][j], res[i][j - 1]);
//                }
//            }
//        }
        return res[m - 1][n - 1];
    }


    /**
     * 45. 跳跃游戏 II
     *
     * @param nums
     * @return
     */
    int min = Integer.MAX_VALUE;

    public int jump(int[] nums) {
//        findRes(0, 0, nums);
//        return min;
        int jump = 0;
        int len = nums.length - 1;
        int max = 0;
        int segment = 0;
        for (int i = 0; i < len; i++) {
            max = Math.max(max, i + nums[i]);
            if (i == segment) {
                segment = max;
                jump++;
            }
        }
        return jump;
    }


    private void findRes(int x, int k, int[] nums) {
        if (x >= nums.length - 1) {
            min = Math.min(min, k);
            System.out.println(min);
            return;
        }
        if (k >= min) {
            return;
        }
        k = k + 1;
        for (int i = 1; i <= nums[x]; i++) {
            findRes(x + i, k, nums);
        }
    }


    /**
     * 44. 通配符匹配
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        boolean[][] res = new boolean[sLen+1][pLen+1];
        res[0][0]=true;
        for (int i = 1; i <= pLen; i++) {
            if(p.charAt(i-1)=='*'){
                res[0][i] = true;
            }else{
                break;
            }
        }
        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= pLen; j++) {
                if(p.charAt(j-1)=='*'){
                    res[i][j] = res[i-1][j]||res[i][j-1];
                }else if(p.charAt(j-1)=='?'||p.charAt(j-1)==s.charAt(i-1)){
                    res[i][j] = res[i-1][j-1];
                }
            }
        }

        for (boolean[] re : res) {
            for (boolean i : re) {
                System.out.print(i+"\t,");
            }
            System.out.println();
        }
        return res[sLen][pLen];
    }


    public static void main(String[] args) {
        /**
         * 50 Pow(x,n)
         */
        Solution solution = new Solution();
        double ans = solution.myPow(2, 10);
        System.out.println(ans);
        /**
         * 子集
         */
        int[] nums = {1, 2, 3};
        List<List<Integer>> subsets = solution.subsets(nums);
        subsets.stream().forEach(System.out::println);

        /**
         * 17、电话号码的字母组合
         */
        List<String> list = solution.letterCombinations("23");
        list.stream().forEach(System.out::println);
        /**
         * 4. 寻找两个正序数组的中位数
         */
        System.out.println("======");
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        solution.findMedianSortedArrays(nums1, nums2);


        /**
         * 74. 搜索二维矩阵
         * @param matrix
         * @param target
         * @return
         */
        System.out.println("==================");
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        int[][] matrix1 = {{1}};
        solution.searchMatrix(matrix1, 2);


        /**
         * 62. 不同路径
         * @param m
         * @param n
         * @return
         */
        System.out.println("=============");
        int paths = solution.uniquePaths(3, 7);
        System.out.println("不同路径 I result:" + paths);

        /**
         * 63. 不同路径 II
         * @param obstacleGrid
         * @return
         */
        System.out.println("=============");
        int[][] obstacleGrid = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        int pathsWithObstacles = solution.uniquePathsWithObstacles(obstacleGrid);
        System.out.println("不同路径 II result:" + pathsWithObstacles);

        /**
         * 1143. 最长公共子序列
         *      *
         * @return
         */
        System.out.println("================");
        int i = solution.longestCommonSubsequence("b", "bab");
        System.out.println("最长公共子序列 result:" + i);

        /**
         *
         * 45. 跳跃游戏 II
         * @param nums
         * @return
         */
        System.out.println("===================");
        int[] numsJump = new int[]{6, 2, 6, 1, 7, 9, 3, 5, 3, 7, 2, 8, 9, 4, 7, 7, 2, 2, 8, 4, 6, 6, 1, 3};
//        int[] numsJump = new int[]{10,9,8,7,6,5,4,3,2,1,1,0};
//        int[] numsJump = new int[]{2,3,1,1,4};
//        int[] numsJump = new int[]{1,2,1,1,1};
        int jump = solution.jump(numsJump);
        System.out.println("jump Result:" + jump);


        /**
         * 44. 通配符匹配
         *
         * @param s
         * @param p
         * @return
         */
        System.out.println("=============");
        boolean aba = solution.isMatch("adceb","*a*b");
//        boolean aba = solution.isMatch("aa","a");
        System.out.println("IsMatch Result:" + aba);
    }
}
