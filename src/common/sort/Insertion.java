package common.sort;

/**
 * 插入排序算法
 *
 * @author yjy
 * @date 2018-05-09 15:17
 */
public class Insertion {

    public static void sort(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 && less(arr[j], arr[j - 1]); j--) {
                swap(arr, j, j - 1);
            }
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
