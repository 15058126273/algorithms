package common.sort;

/**
 * 快速排序 (三向切分排序)
 *
 * @author yjy
 * @date 2018-05-11 10:28
 */
public class QuickThree {

    public static void sort(Comparable[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(Comparable[] arr, int begin, int end) {
        if (begin >= end) return;
        Comparable v = arr[begin];
        int lt = begin, gt = end, i = begin + 1;
        while (i <= gt) {
            int cmp = arr[i].compareTo(v);
            if (cmp < 0) swap(arr, lt++, i++);
            else if (cmp > 0) swap(arr, gt--, i);
            else i++;
        }
        sort(arr, begin, lt - 1);
        sort(arr, gt + 1, end);
    }

    // 交换元素
    private static void swap(Comparable[] arr, int a, int b) {
        Comparable temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
