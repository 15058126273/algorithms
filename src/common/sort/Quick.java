package common.sort;

/**
 * 快速排序 (切分排序)
 *
 * @author yjy
 * @date 2018-05-11 10:28
 */
public class Quick {

    public static void sort(Comparable[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(Comparable[] arr, int begin, int end) {
        if (begin >= end) return;
        int j = partition(arr, begin, end);
        sort(arr, begin, j);
        sort(arr, j + 1, end);
    }

    // 切分指定范围的数组
    private static int partition(Comparable[] arr, int begin, int end) {
        Comparable v = arr[begin];
        int i = begin + 1, j = end;
        while (true) {
            while (arr[i].compareTo(v) <= 0 && i++ < end);
            while (arr[j].compareTo(v) >= 0 && j-- > i);
            if (i >= j) break;
            swap(arr, i, j);
        }
        swap(arr, begin, j);
        return j;
    }

    // 交换元素
    private static void swap(Comparable[] arr, int a, int b) {
        Comparable temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
