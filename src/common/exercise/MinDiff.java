package common.exercise;

import java.util.Arrays;

/**
 * @description: 最小差
 * 若给定两个整数数组（第一个是数组 A，第二个是数组 B），在数组 A 中取 A[ i ]，数组 B 中取 B[ j ]，A[ i ] 和 B[ j ] 两者的差越小越好( | A[ i ] - B[ j ] | )，写一个函数返回最小差。
    挑战 ：
    时间复杂度 O(n log n)
    格式：
    输入行输入两个整数数组 A 和 B，最后输出返回的最小差。
    样例输入
    A = [ 15，4，6，7 ]
    B = [ 2，3，8，9 ]
    样例输出
    0
 * @author: yjy
 * @datetime: 2017/12/28 10:19
 */
public class MinDiff {

    public static void main(String[] s) {
        int[] a = {14, 54, 545, 105}, b = {42, 158, 511, 413};
        System.out.println(minDiffUseNormal(a, b));
        System.out.println(minDiffUseSort(a, b));
    }

    /**
     * 获取最小差 > 时间复杂度 O(n2)
     * @param a 数组a
     * @param b 数组b
     * @return 最小差
     */
    public static int minDiffUseNormal(int[] a, int[] b) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                int diff = Math.abs(a[i] - b[j]);
                if (diff < min) {
                    min = diff;
                }
            }
        }
        return min;
    }

    /**
     * 获取最小差 > 时间复杂度 O(n log n)
     * @param a 数组a
     * @param b 数组b
     * @return 最小差
     */
    public static int minDiffUseSort(int[] a, int[] b) {
        int min = Integer.MAX_VALUE, aLen = a.length;
        Arrays.sort(a);
        for (int i : b) {
            int minDiff = Integer.MAX_VALUE;
            for (int j = aLen / 2; j >= 0 && j < aLen;) {
                int x = a[j], diff;
                if (i > x) {
                    diff = i - x;
                    j ++;
                } else if (i < x) {
                    diff = x - i;
                    j --;
                } else {
                    return 0;
                }
                if (diff < minDiff) {
                    minDiff = diff;
                } else {
                    break;
                }
            }
            if (minDiff < min) {
                min = minDiff;
                if (min == 0) {
                    break;
                }
            }
        }
        return min;
    }

}
