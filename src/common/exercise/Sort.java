package common.exercise;

import java.util.Arrays;

/**
 * @description: 数组排序
 * @author: yjy
 * @datetime: 2018/01/02 16:05
 */
public class Sort {

    public static void main(String[] s) {
        int[] arr = {2,9,6,3,2,2,7,3,1};
        Arrays.stream(bubbleSort(Arrays.copyOf(arr, arr.length))).forEachOrdered(x -> System.out.print(x + ","));
        System.out.println();
        Arrays.stream(chooseSort(Arrays.copyOf(arr, arr.length))).forEachOrdered(x -> System.out.print(x + ","));
        System.out.println();
        Arrays.stream(insertSort(Arrays.copyOf(arr, arr.length))).forEachOrdered(x -> System.out.print(x + ","));
        System.out.println();
        Arrays.stream(fastSort(Arrays.copyOf(arr, arr.length), 0, arr.length - 1)).forEachOrdered(x -> System.out.print(x + ","));
        System.out.println();
    }

    /**
     * 冒泡排序
     * @param arr 数组
     * @return 排序后数组
     */
    public static int[] bubbleSort(int[] arr){
        for(int i = 0;i < arr.length;i++){
            //比较两个相邻的元素
            for(int j = 0;j < arr.length-i-1;j++){
                if(arr[j] > arr[j+1]){
                    int t = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = t;
                }
            }
        }
        return arr;
    }

    /**
     * 选择排序
     * @param arr 数组
     * @return 排序后数组
     */
    public static int[] chooseSort(int[] arr) {
        for (int i = 0; i < arr.length; i ++) {
            int m = i;
            for (int x = i + 1; x < arr.length; x++) {
                if (arr[x] < arr[m]) {
                    m = x;
                }
            }
            if (i != m) {
                arr[i] += arr[m];
                arr[m] = arr[i] - arr[m];
                arr[i] = arr[i] - arr[m];
            }
        }
        return arr;
    }

    /**
     * 插入排序
     * @param arr 数组
     * @return 排序后数组
     */
    public static int[] insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (temp < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
                if (j == -1) {
                    break;
                }
            }
            arr[j + 1] = temp;
        }
        return arr;
    }

    /**
     * 快速排序
     * @param arr 数组
     * @param left 左边下标
     * @param right 右边下标
     * @return 排序后数组
     */
    public static int[] fastSort(int[] arr, int left, int right) {
        System.out.println(left + ":" + right);
        if (left < right) {
            int s = arr[left];
            int i = left;
            int j = right + 1;
            while (true) {
                while (i + 1 < arr.length && arr[++i] < s);
                while (j - 1 >= 0 && arr[--j] > s);
                if (i >= j) {
                    break;
                } else {
                    arr[i] += arr[j];
                    arr[j] = arr[i] - arr[j];
                    arr[i] = arr[i] - arr[j];
                }
            }
            arr[left] = arr[j];
            arr[j] = s;
            fastSort(arr, left, j - 1);
            fastSort(arr, j + 1, right);
        }
        return arr;
    }

}
