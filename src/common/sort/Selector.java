package common.sort;

/**
 * 选择排序算法
 *
 * @author yjy
 * @date 2018-05-09 15:17
 */
public class Selector {

    public static void sort(Comparable[] arr) {
        for (int finish = 0; finish < arr.length; finish++) {
            int min = finish;
            for (int i = finish; i < arr.length; i++) {
                if (less(arr[i], arr[min])) {
                    min = i;
                }
            }
            if (finish != min)
                swap(arr, finish, min);
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
