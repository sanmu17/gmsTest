package com.data;

/**
 * 类描述：解决八皇后问题
 *
 * @ClassName recursion_8queen
 * @Description TODO
 * @Author 1537414333@qq.com
 * @Date 2021/8/29 11:50
 * @Version 1.0
 */
public class Recursion_8queen {
    int max = 8;//定义数组的长度为8
    int[] arr = new int[max];//存放 解
    int count = 1;
    public static void main(String[] args) {
        Recursion_8queen queen = new Recursion_8queen();
        queen.putQueen(0);
    }

    /**
     * 放置皇后进入arr 递归调用
     * @param n 当前第n个皇后
     */
    private void putQueen(int n){
        if(n == max){
            for (int i : arr) {
                System.out.print(i+" ");
            }
            System.out.println("-------------第"+(count++)+"种解法");
            return;
        }

        for (int i = 0; i < max; i++) {
            //有冲突 皇后就换到下一个位置
            arr[n]=i;
            //判断当前的皇后位置是否与之前的有冲突
            if(judge(n)){
                //没有冲突 就放置下一个皇后
                putQueen(n+1);
            }
        }

    }


    /**
     * 判断当前放置的位置和前面位置是否再同一列或一条斜线上
     * @param n 第几个皇后
     * @return true 表示不冲突
     */
    private boolean judge(int n){
        for (int i = 0; i < n; i++) {
            //判断当前放置的位置和前面位置是否再同一列或一条斜线上
            if(arr[i] == arr[n]||Math.abs(n-i)==Math.abs(arr[i]-arr[n])){
                return false;
            }
        }
        return true;
    }
}
