package com.data;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.fill;

public class huaweiODQ {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int[] score = new int[n];
//        for (int i = 0; i < n; i++) {
//            score[i] = scanner.nextInt();
//        }
//        int k = scanner.nextInt();
//
//        System.out.println(maxScore(n,score,k));

//getMinOffsetByMem
//        int total = 100;
//        int n = scanner.nextInt();
//        List<Integer[]> mem = new ArrayList<>();
//        while (scanner.hasNextInt()) {
//            int x = scanner.nextInt();
//            int y = scanner.nextInt();
//            mem.add(new Integer[]{x, y});
//        }
//
//        System.out.println(getMinOffsetByMem(total, n, mem));

//        int n = scanner.nextInt();
//        int[] gems = new int[n];
//        for (int i = 0; i < n; i++) {
//            gems[i] = scanner.nextInt();
//        }
//        int value = scanner.nextInt();
//        System.out.println(maxGems(gems, value));

//        System.out.println(minDifference(new Integer[]{13, 9, 8, 7, 6, 5, 4, 3, 2, 1}));

        System.out.println(getMaxSum(7, new int[]{1, 6, 4, 7, 3, 6, 1}, 8));
    }

    private static int getMaxSum(int len, int[] nums, int k) {
        if (len <= k) return Arrays.stream(nums).sum();
        int[] res = new int[k];
        int left = 0, right = len - 1;
        int n = k;
        int max = 0;
        while (left < right && n > 0) {
            if (nums[left] == nums[right]) {
                while (left < right && nums[left] == nums[right] && n > 0) {
                    max += nums[left];
                    res[k - n] = max;
                    n--;
                    left++;
                    right--;
                }
            } else {
                max = Math.max(nums[left], nums[right]) + max;
                res[k - n] = max;
                n--;
                left++;
                right--;
            }
        }
        for (int re : res) {
            System.out.print(re + "  ,");
        }
        System.out.println();
        return res[k - 1];
    }

    public static int minDifference(Integer[] scores) {
        int[] visited = new int[scores.length];
        int min = 0;
        int sum = Arrays.stream(scores).mapToInt(a -> a).sum();
        for (; min < sum; min++) {
            int target = sum - min;
            if (target % 2 == 0) {
                if (dfs(target / 2, scores, 0, visited)) {
                    return min;
                }
            }
        }
        return min;
    }

    private static boolean dfs(int target, Integer[] scores, int index, int[] visited) {
        int len = scores.length;
        if (index > 5 && target < 0) {
            return false;
        }
        if (index == 5 && target == 0) {
            return true;
        }
        for (int i = 0; i < len; i++) {
            if (visited[i] != 0) {
                continue;
            }
            visited[i] = 1;
            if (dfs(target - scores[i], scores, index + 1, visited)) {
                return true;
            }
            visited[i] = 0;
        }
        return false;
    }

    public static int maxGems(int[] gems, int value) {
        if (gems.length == 0) return 0;

        int max = 0;
        for (int i = 0; i < gems.length; i++) {
            int total = 0, count = 0;
            int j = i;
            while (j < gems.length && total + gems[j] <= value) {
                total += gems[j];
                count = j - i + 1;
                j++;
            }
            max = Math.max(count, max);
        }
        return max;
    }

    /**
     * 一个总空间为100字节的堆，现要从中新申请一块内存， 内存分配 原则为:优先紧接着前一块已使用内存，分配空间足够目最接近申请大小的空
     * 存。
     * 输入描述
     * 第1行是1个整数，表示期望申请的内存字节数。
     * 第2到第N行是用空格分割的两个整数，表示当前已分配的内存的情况，每一行表示一块已分配的连续内存空间，每行的第1和第2个整教分别表示偏
     * 和内存块大小，如:
     * 0 1
     * 3 2
     * 表示0偏移地址开始的1个字节和3偏移地址开始的2个字节已被分配，其余内存空闲。
     * 输出描述
     * 若申请成功，输出申请到内存的偏移
     * 若申请失败，输出-1。
     * 备注
     * 1.若输入信息不合法或无效，则申请失败2.若没有足够的空间供分配，则申请失败3. 堆内存 信息有区域重叠或有非法值等都是无效输入
     * 示例1：
     * 输入：
     * 1
     * 0 1
     * 3 2
     * 输出：
     * 1
     * 说明
     * 堆中已使用的两块内存是偏移从0开始的1字节和偏移从3开始的2字节，空闲的两块内存是偏移从1开始2个字节和偏移从5开始95字节根据分配原则
     * 请的内存应从1开始分配1个字节，所以输出偏移为1。
     *
     * @param totalMem
     * @param n
     * @param mem
     * @return
     */
    private static int getMinOffsetByMem(int totalMem, int n, List<Integer[]> mem) {
        int[] m = new int[totalMem];
        for (Integer[] arr : mem) {
            int x = arr[0], y = arr[1];
            Arrays.fill(m, x, x + y, 1);
        }

        for (int i = 0; i < m.length - n; i++) {
            if (m[i] == 0) {
                int enoughMem = judgeEnoughMem(n, m, i);
                if (enoughMem == 0) return i;
                i += enoughMem;
            }
        }
        return -1;
    }

    /**
     * 从m数组的第i个数开始判断接下来的n个数是否都是0，是则返回0，否则返回不为0的坐标
     *
     * @param n
     * @param m
     * @param i
     * @return
     */
    private static int judgeEnoughMem(int n, int[] m, int i) {
        for (int j = 0; j < n; j++) {
            if (m[j + i] != 0) {
                return j + i;
            }
        }
        return 0;
    }


    /**
     * 小明和朋友们一起玩跳格子游戏，每个格子上有特定的分数score = [1 -1-6 7 -17 7]，从起点score[0]开始，每次最大的步长为k，请你返回小明跳到
     * score[n-1]时，能得到的最大得分。
     * 注
     * 格子的总长度和步长的区间在[1，100000]
     * 每个格了的分数在[-10000,10000]区间中
     * 输入描述
     * 6//第一行输入总的格了数量
     * 1 -1 -6 7 -17 7/第二行输入每个格子的分数score[i]
     * 2//第三行输入最大跳的步长k
     * 输出描述：
     * 一个整数代表最大得分。
     * 示例1：
     * 输入：
     * 6
     * 1 -1 -6 7 -17 7
     * 2
     * 输出：
     * 14
     */
    public static int maxScore(int n, int[] score, int k) {
        // 初始化DP数组
        int[] dp = new int[n];
        fill(dp, Integer.MIN_VALUE);
        dp[0] = score[0]; // 起点的分数

        for (int i = 1; i < n; i++) {

            int temp = Integer.MIN_VALUE;//使用临时变量保存当前i轮最大的得分

            for (int j = i - k; j <= i - 1; j++) {
                if (j >= 0) {
                    //在[i-k,i-1]范围内找到最大的得分
                    temp = Math.max(temp, dp[j] + score[i]);
                }
            }

            // 更新DP数组
            dp[i] = temp;
        }

        return dp[n - 1];
    }
}

