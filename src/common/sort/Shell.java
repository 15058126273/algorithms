package common.sort;

/**
 * 希尔排序算法
 *
 * @author yjy
 * @date 2018-05-09 15:17
 */
public class Shell {

    public static void sort(Comparable[] arr) {
        // 增量h
        int h = 1, len = arr.length;
        while (h < len / 3) h = 3 * h + 1;

        while (h >= 1) {
            for (int i = h; i < len; i++) {
                for (int j = i; j >= h && less(arr[j], arr[j - h]); j -= h) {
                    swap(arr, j, j - h);
                }
            }
            h /= 3;
        }

    }

    // 比较元素, a < b -> true
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    // 交换元素
    private static void swap(Comparable[] arr, int a, int b) {
        Comparable temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
