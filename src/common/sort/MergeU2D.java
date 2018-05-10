package common.sort;

import java.util.Arrays;

/**
 * 并归排序 自顶向下
 *
 * @author yjy
 * @date 2018-05-10 16:06
 */
public class MergeU2D {

    public static void main(String[] args) {
        // just do it...
        Integer[] arr = {8,6,4,2,7,5,3,1,8};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(Comparable[] arr) {
        int len = arr.length;
        Comparable[] res = new Comparable[len];
        merge(arr, res, 0, (len - 1) >> 1, len - 1);
    }

    private static void merge(Comparable[] arr, Comparable[] res, int begin, int mid, int end) {
        assert begin <= end;
        if (begin == end) return;

        // 合并左部分
        merge(arr, res, begin, (begin + mid) >> 1, mid);
        // 合并右部分
        merge(arr, res, mid + 1, (mid + end + 1) >> 1, end);

        System.arraycopy(arr, begin, res, begin, end - begin + 1);

        for (int i = begin, x = begin, j = mid + 1; i <= end; i++) {
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

