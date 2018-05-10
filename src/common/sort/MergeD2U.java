package common.sort;

import java.util.Arrays;

/**
 * 并归排序 自底向上
 *
 * @author yjy
 * @date 2018-05-10 16:06
 */
public class MergeD2U {

    public static void main(String[] args) {
        // just do it...
        Integer[] arr = {8, 6, 4, 2, 7, 5, 7, 3, 9, 3, 1};
        sort(arr);

        System.out.println(Arrays.toString(arr));
    }

    public static void sort(Comparable[] arr) {
        int len = arr.length;
        Comparable[] res = new Comparable[len];
        for (int i = 1; i < len; i <<= 1) {
            for (int x = 0; x < len; x += i << 1) {
                merge(arr, res, x, x + i - 1, Math.min(x + (i << 1) - 1, len - 1));
            }
        }
    }

    // 合并两个有序的集合
    private static void merge(Comparable[] arr, Comparable[] res, int begin, int mid, int end) {
        assert begin <= end;
        if (begin == end) {
            return;
        }

        // 将需要合并的数据 copy 至 res
        System.arraycopy(arr, begin, res, begin, end - begin + 1);

        int x = begin, j = mid + 1;
        for (int i = begin; i <= end; i++) {
            if (x > mid) arr[i] = res[j++];
            else if (j > end) arr[i] = res[x++];
            else if (less(res[x], res[j])) arr[i] = res[x++];
            else arr[i] = res[j++];
        }
    }

    // 比较元素, a < b -> true
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

}

