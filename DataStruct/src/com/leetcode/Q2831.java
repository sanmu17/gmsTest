package com.leetcode;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 2831. 找出最长等值子数组
 * 中等
 * 相关标签
 * 相关企业
 * 提示
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。
 * <p>
 * 如果子数组中所有元素都相等，则认为子数组是一个 等值子数组 。注意，空数组是 等值子数组 。
 * <p>
 * 从 nums 中删除最多 k 个元素后，返回可能的最长等值子数组的长度。
 * <p>
 * 子数组 是数组中一个连续且可能为空的元素序列。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,3,2,3,1,3], k = 3
 * 输出：3
 * 解释：最优的方案是删除下标 2 和下标 4 的元素。
 * 删除后，nums 等于 [1, 3, 3, 3] 。
 * 最长等值子数组从 i = 1 开始到 j = 3 结束，长度等于 3 。
 * 可以证明无法创建更长的等值子数组。
 * 示例 2：
 * <p>
 * 输入：nums = [1,1,2,2,1,1], k = 2
 * 输出：4
 * 解释：最优的方案是删除下标 2 和下标 3 的元素。
 * 删除后，nums 等于 [1, 1, 1, 1] 。
 * 数组自身就是等值子数组，长度等于 4 。
 * 可以证明无法创建更长的等值子数组。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= nums.length
 * 0 <= k <= nums.length
 */
public class Q2831 {
    public static void main(String[] args) {
//        List<Integer> nums = List.of(4, 2, 7, 2, 1, 7, 2);
        List<Integer> nums = List.of(6, 4, 7, 6, 4, 8, 6, 6);
        int k = 1;
        int res = longestEqualSubarray(nums, k);
        System.out.println(res);
    }

    public static int longestEqualSubarray(List<Integer> nums, int k) {
        int ans = 1;
        Map<Integer, Integer> numAndCount = new HashMap<>();
        int low = 0, high, count = 0;
        for (high = 0; high < nums.size(); high++) {
            Integer i = nums.get(high);
            numAndCount.put(i, numAndCount.getOrDefault(i, 0) + 1);
            count = Math.max(count, numAndCount.get(i));
            while (high - low + 1 - count > k) {
                Integer m = nums.get(low);
                numAndCount.put(m, numAndCount.get(m) - 1);
                low++;
            }
            ans = Math.max(ans, count);
        }
        return ans;
    }
}
