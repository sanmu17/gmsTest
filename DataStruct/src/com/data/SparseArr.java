package com.data;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 二维数组转稀疏数组
 */
public class SparseArr {
    public static void main(String[] args) {
        int chessArr[][]= new int[11][11];
        chessArr[1][2]=1;
        chessArr[2][3]=2;
        chessArr[5][7]=8;
        System.out.println("原始的二维数组--------");
        for (int[] rows : chessArr) {
            for (int data : rows) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        /**
         * 二维数组转稀疏数组
         */
        int[][] sparseArr = getSparseArr(chessArr);
        System.out.println();
        System.out.println("稀疏数组-----");
        for (int[] row : sparseArr) {
            System.out.printf("%d\t%d\t%d\t\n",row[0],row[1],row[2]);
        }
        System.out.println();
        /**
         * 将稀疏数组存放到磁盘上
         */
        saveSpareArrToSSD(sparseArr);

        /**
         * 稀疏数组转二维数组
         */
        spareArrToNormalArr(sparseArr);
    }

    /**
     * 二维数组转稀疏数组
     * @param chessArr
     * @return 稀疏数组
     * 稀疏数组 （m,3）m行3列:
     *                      第一行：二维数组的行数，列数，（不为0）值的个数（即m-1）
     *                      第二行：第一个值的行坐标，列坐标，值
     *                      第三行：与第二行类似，第二个值的行列坐标，值（以此类推，直到第m个值）
     */
    private static int[][] getSparseArr(int[][] chessArr) {
        int num=0;
        for (int i = 0; i < chessArr.length ; i++) {
            for (int j = 0; j < 11; j++) {
                if(chessArr[i][j]!=0){
                    num++;
                }
            }
        }
        int sparseArr[][]=new int[num+1][3];
        sparseArr[0][0]=11;
        sparseArr[0][1]=11;
        sparseArr[0][2]=num;

        int count=0;
        for (int i = 0; i < chessArr.length ; i++) {
            for (int j = 0; j < 11; j++) {
                if(chessArr[i][j]!=0){
                     count++;
                     sparseArr[count][0]=i;
                     sparseArr[count][1]=j;
                     sparseArr[count][2]= chessArr[i][j];
                }
            }
        }
        return sparseArr;
    }

    /**
     * 疏数组转二维数组
     * @param sparseArr
     */
    private static void spareArrToNormalArr(int[][] sparseArr) {
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        //遍历系稀疏数组，把数据放入二维数组对应位置
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]]= sparseArr[i][2];
        }

        System.out.println("稀疏数组转二维数组--------");
        for (int[] rows : chessArr2) {
            for (int data : rows) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }

    /***
     *
     * @param sparseArr  稀疏数组
     *    将稀疏数组存放到磁盘上
     */
    private static void saveSpareArrToSSD(int[][] sparseArr) {
        FileWriter writer=null;
        try {
            File file = new File("data.txt");
            writer = new FileWriter(file);
            for (int[] row : sparseArr) {
                writer.write( row[0]+"\t "+row[1]+"\t "+row[2]);
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(writer!=null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
