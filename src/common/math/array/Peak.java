package common.math.array;

import java.util.Arrays;

/**
 * 求数组中的峰值
 */
public class Peak {

    public static void main(String[] rags) {
        // 样本
        int[] x = {747342,3436,3435433,4235462,54363,255245,5436572,546352,6363445,4562423};
        // 高峰
        Arrays.stream(findBigPeak(x)).filter(i -> i != 0).forEach(System.out::print);
        System.out.println();
        // 低峰
        Arrays.stream(findSmallPeak(x)).filter(i -> i != 0).forEach(System.out::print);
        System.out.println();
        // 高低峰
        Arrays.stream(findAllPeak(x)).filter(i -> i != 0).forEach(System.out::print);
    }

    /**
     * 获取所有高峰下标
     * @param arr 给定数组
     * @return 高峰下标数组
     */
    private static int[] findBigPeak(int[] arr) {
        int[] index;
        int len = arr.length;
        if (len < 3) {
            index = new int[0];
        } else {
            index = new int[len / 3];
            for (int i = 1, count = 0; i < len - 1; i++) {
                if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                    index[count] = i;
                    count++;
                    i++;
                }
            }
        }
        return index;
    }

    /**
     * 获取所有低峰下标
     * @param arr 给定数组
     * @return 低峰下标数组
     */
    private static int[] findSmallPeak(int[] arr) {
        int[] index;
        int len = arr.length;
        if (len < 3) {
            index = new int[0];
        } else {
            index = new int[len / 3];
            for (int i = 1, count = 0; i < len - 1; i++) {
                if (arr[i] < arr[i - 1] && arr[i] < arr[i + 1]) {
                    index[count] = i;
                    count++;
                    i++;
                }
            }
        }
        return index;
    }

    /**
     * 获取所有峰值
     * @param arr 给定数组
     * @return 所有峰值下标数组
     */
    private static int[] findAllPeak(int[] arr) {
        int[] index;
        int len = arr.length;
        if (len < 3) {
            index = new int[0];
        } else {
            index = new int[len - 2];
            for (int i = 1, count = 0; i < len - 1; i++) {
                if (arr[i] > arr[i - 1] == arr[i] > arr[i + 1]) {
                    index[count] = i;
                    count++;
                }
            }
        }
        return index;
    }

}
