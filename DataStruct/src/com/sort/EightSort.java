package com.sort;

import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：八大排序算法总结
 *
 * @ClassName EightSort
 * @Description TODO
 * @Author 1537414333@qq.com
 * @Date 2021/10/7 11:10
 * @Version 1.0
 */
public class EightSort {
    public static void main(String[] args) {
        int[] data = {1, 34, 31, 2, 65, 87, 255, 8, 33, 64, 3};
//        int[] sort = bubbleSort(data);
//        int[] sort = easySelect(data);
//        int[] sort = directInsertSort(data);
//        int[] sort = shellSort(data);
//        int[] sort = quickSort(data,0,data.length-1);
//        int[] sort = heapSort(data);
//        printSort(sort);
//        mSort(data, 0, data.length - 1);
        bucketSort(data);
        printSort(data);
    }

    public static void printSort(int[] arr) {
        for (int i : arr) {
            System.out.print(i + "  ");
        }
    }

    /**
     * 1、
     * 冒泡排序算法：
     * 1.将序列中所有的元素两两比较
     * 2.将剩余序列的所有元素两两比较，将最大的放到最后面
     * 3.重复第二步，知道最后一个数
     *
     * @param arr
     * @return
     */
    public static int[] bubbleSort(int[] arr) {
        int temp;
        boolean flag = false;//判断在这一趟排序中是否有交换过
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {//（最大的数放到了最后面）所以比较的个数每一趟都在减少
                if (arr[j + 1] < arr[j]) {//前面的数比后面的大，交换
                    flag = true;
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
            if (flag) {//交换过顺序，接着下一趟排序
                flag = false;
            } else {//没有，就直接退出排序，已经排完序了，所以这一趟没有交换
                break;
            }
        }
        return arr;
    }

    /**
     * 2、
     * 简单选择排序：
     * 常用于取序列数中最大最小的几棵树
     * (如果每次比较都交换，那么就是交换排序；如果每次比较完一个循环再交换，就是简单选择排序。)
     * 1.遍历整个序列，将最小的数放在最前面
     * 2.遍历剩余的序列，将最小的数字放在最前面
     * 3.重复步骤2，知道剩余最后一个数字。
     *
     * @param arr
     * @return
     */
    public static int[] easySelect(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            int index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    index = j;
                }
            }
            arr[index] = arr[i];
            arr[i] = min;
        }
        return arr;
    }

    /**
     * 3、
     * 直接插入排序：
     * 将新的数据插入已经排好的数据列中。
     * 将第一个和第二个数排序，构成有序数列
     * 然后将第三个数插进去，构成新的有序数列，后面的数重复这个步骤
     *
     * @param arr
     * @return
     */
    public static int[] directInsertSort(int[] arr) {
        int temp;
        for (int i = 1; i < arr.length; i++) {
            temp = arr[i];
            int index = i - 1;
            while (index > 0 && temp < arr[index]) {
                arr[index + 1] = arr[index];
                index--;
            }
            arr[index + 1] = temp;
        }
        return arr;
    }

    /**
     * 4、
     * 希尔排序：(简单(直接)插入排序的一种改进)
     * 对于直接插入的数，数据量巨大：
     * 1.将数的个数设置为n，取奇数k = n/2,将下标的差值k的数分为一组，构成有序数列。
     * 2.再取k = k/2,将下标差值为k的数构成一组，构成有序数列，
     * 3.重复第二步，直到k=1执行简单的插入排序
     *
     * @param arr
     * @return
     */
    public static int[] shellSort(int[] arr) {
        int gap = arr.length;
        int temp;
        while (gap > 0) {
            gap = gap / 2;
            for (int x = 0; x < gap; x++) {
                for (int i = x + gap; i < arr.length; i += gap) {
                    temp = arr[i];
                    int index = i - gap;
                    while (index > 0 && temp < arr[index]) {
                        arr[index + gap] = arr[index];
                        index -= gap;
                    }
                    arr[index + gap] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * 5、
     * 快速排序：
     * 要求时间最快
     * 1.选择第一个数作为P,小于P的放左边，大于p的放右边
     * 2.递归将p的左边和右边的数按照步骤一进行，直到不能递归
     *
     * @param arr
     * @return
     */
    public static int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pivot = arr[left];
            int temp;
            int low = left, high = right;
            while (low <= high) {
                while (arr[low] < pivot && low < right) {
                    low++;
                }
                while (arr[high] > pivot && high > left) {
                    high--;
                }
                if (low <= high) {
                    temp = arr[low];
                    arr[low] = arr[high];
                    arr[high] = temp;
                    low++;
                    high--;
                }
            }
            if (left < high) {
                quickSort(arr, left, high);
            }
            if (right > low) {
                quickSort(arr, low, right);
            }
        }
        return arr;
    }

    /**
     * 6、
     * 堆排序：
     * 1.将序列构建为大顶堆
     * 2.将根节点与最后一个节点兑换，然后断开最后一个节点
     * 3.重复一二步骤，直到所有节点断开
     *
     * @param arr
     * @return
     */
    public static int[] heapSort(int[] arr) {
        int length = arr.length;
        int temp;
        for (int i = 0; i < length - 1; i++) {
            buildMaxHeap(arr, length - 1 - i);
            temp = arr[0];
            arr[0] = arr[length - 1 - i];
            arr[length - 1 - i] = temp;
        }
        return arr;
    }

    public static void buildMaxHeap(int[] arr, int last) {
        int temp;
        for (int i = (last - 1) / 2; i >= 0; i--) {
            int x = i;
            //当前节点存在子节点
            while (x * 2 + 1 < last) {
                //左子节点索引
                int leftSub = 2 * x + 1;
                if (leftSub < last) {
                    //右子节点比左子节点大，拿到右子节点的索引
                    if (arr[leftSub] < arr[leftSub + 1]) {
                        leftSub++;
                    }
                }
                //x节点的值小于其较大的子节点，交换值（调整）,并且转到下一个子节点
                if (arr[x] < arr[leftSub]) {
                    temp = arr[x];
                    arr[x] = arr[leftSub];
                    arr[leftSub] = temp;
                    x = leftSub;
                } else {
                    break;
                }
            }
        }
    }


    /**
     * 7、
     * 归并排序;
     * 速度仅次于快排，内存少的时候使用，可以进行并行运算的时候使用。
     * 1.选择相邻两个数组成的有序序列
     * 2.选择相邻的两个有序序列组成的一个有序序列
     * 3.重复步骤二，直到组成一个有序序列
     * <p>
     * 将一个数组分开成小的数组，在调用merge进行合并
     *
     * @param arr
     * @param left
     * @param right
     */
    public static void mSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mSort(arr, left, mid);
        mSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    /**
     * 对分开后的数据进行有序合并
     *
     * @param arr
     * @param left
     * @param mid
     * @param right
     */
    public static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int t = 0;
        int i = left;
        int j = mid + 1;
        //将arr中的对应数据排序，放入临时数组
        //执行完这个while循环，相当于将两个子序列合并后重新进行了一次排序并将排序结果记录在了临时数组temp[k]中。
        // while走完后k的值等于数组的长度，i的值此时大于mid，j的值大于right
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        //若右边已经排完序，左边数组还有剩余，加在临时数组后面即可
        while (i <= mid) {
            temp[t++] = arr[i++];
        }
        //左边数组已经排序完毕，右边数组还有数据，将右边数组的数据放入临时数组即可
        while (j <= right) {
            temp[t++] = arr[j++];
        }
        //将临时数组中的有序数据赋值给arr[]
        for (int k = 0; k < temp.length; k++) {
            arr[left + k] = temp[k];
        }
    }

    /**
     * 基数排序：(未处理负数)
     * 1.将所有的数的个数取出来，按照个位数排序，构成序列
     * 2.将新构成的所有数的十位数取出，按照十位数进行排序
     *
     * @param arr
     */
    public static void bucketSort(int[] arr) {
        //基数排序思想：将所有待比较数值统一为同样的数位长度，数位较短的数前面补0，然后，从最低位开始，一次进行依次排序，
        //这样从最低位排序一直到最高位完成以后，数列就变成一个有序序列

        //获得数组中的最大数据的位数
        int maxBit = getMaxBit(arr);

        for (int i = 1; i <= maxBit; i++) {
            //根据第i位数字进行桶排序
            //初始化十个List进行桶排序的数据存放
            List<List<Integer>> list = Lists.newArrayList();
            for (int j = 0; j < 10; j++) {
                list.add(new ArrayList<>());
            }
            for (int j = 0; j < arr.length; j++) {
                list.get(getNBit(arr[j], i)).add(arr[j]);
            }

            int k = 0;
            for (List<Integer> integers : list) {
                for (Integer integer : integers) {
                    arr[k++] = integer;
                }
            }
        }
    }

    /**
     * 获取数组中的最大位数
     *
     * @param arr
     * @return
     */
    private static int getMaxBit(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int maxBit = 1;
        while (max / 10 > 0) {
            max /= 10;
            maxBit++;
        }
        return maxBit;
    }

    /**
     * 获取数据指定位数的数字
     *
     * @param n
     * @param bit
     * @return
     */
    public static int getNBit(int n, int bit) {
        int temp = (int) (n / (Math.pow(10, bit-1)));
        return temp % 10;
    }
}
