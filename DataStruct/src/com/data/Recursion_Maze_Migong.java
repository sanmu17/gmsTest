package com.data;

/**
 * 类描述：迷宫问题 使用递归 没有使用最短路径算法
 * 寻找通路
 *
 * @ClassName Maze_Migong
 * @Description TODO
 * @Author 1537414333@qq.com
 * @Date 2021/8/29 12:14
 * @Version 1.0
 */
public class Recursion_Maze_Migong {

    public static void main(String[] args) {
        int[][] map = new int[8][7];
        for (int i = 0; i < 7; i++) {
            map[0][i]=1;
            map[7][i]=1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0]=1;
            map[i][6]=1;
        }
        map[3][1]=1;
        map[3][2]=1;

        System.out.println("=====初始地图====");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+" \t");
            }
            System.out.println();
        }
        setWay(map,1,1);

        System.out.println("=====寻找通路之后的地图====");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+" \t");
            }
            System.out.println();
        }

    }

    /**
     *
     * @param map 代表迷宫的地图
     * @param i
     * @param j
     * i,j地图初始位置
     */
    public static boolean setWay(int[][] map,int i,int j){
        if(map[6][5]==2){//通路已经找到
            return true;
        }else{//还没找到
            if(map[i][j]==0){//当前节点还没有走过
                map[i][j]=2;
                if(setWay(map,i+1,j)){
                    return true;
                } else if(setWay(map,i,j+1)){
                    return true;
                } else if(setWay(map,i-1,j)){
                    return true;
                } else if(setWay(map,i,j-1)){
                    return true;
                }else{
                    map[i][j]=3;//不是通路
                    return false;
                }
            }else {
                return false;
            }
        }
    }

}
