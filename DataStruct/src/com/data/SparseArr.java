package com.data;


/**
 * 二维数组转稀疏数组
 */
public class SparseArr {
    public static void main(String[] args) {
        int chessArr[][]= new int[11][11];
        chessArr[1][2]=1;
        chessArr[2][3]=2;
        System.out.println("原始的二维数组--------");
        for (int[] rows : chessArr) {
            for (int data : rows) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
}
