package com.example.demo;

import java.util.*;

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
//        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((o1,o2)->o1[0]!=o2[0]?o2[0]-o1[0]:o2[1]-o1[1]);
//        Deque<Integer> deque = new LinkedList<>();
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int[][] nums = new int[n][3];
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < 3; j++) {
//                nums[i][j] = scanner.nextInt();
//            }
//        }
//        printNotWell(nums);
        String s = "asd";
        System.out.println(s.substring(0, 1) + "Q" + s.substring(2));
    }

    private static void printNotWell(int[][] nums) {
        int count = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i == j){
                    continue;
                }
                if(nums[i][0] < nums[j][0] && nums[i][1] < nums[j][1] && nums[i][2] < nums[j][2]){
                    count++;
                    break;
                }
            }
        }
        System.out.println(count);
    }
}
