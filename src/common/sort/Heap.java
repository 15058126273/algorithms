package common.sort;

import common.util.TimeClocker;

/**
 * 堆排序
 *
 * @author yjy
 * 2018-05-15 13:57
 */
public class Heap {

    public static void sort(Comparable[] arr) {
        int len = arr.length;
        TimeClocker timeClocker = new TimeClocker();
        for (int i = len >> 1; i >= 0; i--) {
            down(arr, i, len);
        }
        timeClocker.stop("heap sorted");
        timeClocker = new TimeClocker();
        for (int size = len; size > 0;) {
            swap(arr, 0, --size);
            down(arr, 0, size);
        }
        timeClocker.stop("all sorted");
    }

    private static void down(Comparable[] arr, int i, int size) {
        while (i < size >> 1) {
            int j = i << 1;
            if (j + 1 < size && less(arr, j , j + 1)) j++;
            if (less(arr, i, j)) {
                swap(arr, i, j);
                i = j;
            } else {
                break;
            }
        }
    }

    private static void up(Comparable[] arr, int i) {
        while (i > 0 && less(arr, i >> 1, i)) {
            swap(arr, i >> 1, i);
            i >>= 1;
        }
    }

    private static boolean less(Comparable[] arr, int i, int j) {
        return arr[i].compareTo(arr[j]) < 0;
    }

    private static void swap(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
