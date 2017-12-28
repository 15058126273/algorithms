package common.exercise;

import java.util.Arrays;

/**
 * @description: 数组自增
 * 给定一个非负数，表示一个数字数组，在该数的基础上 +1，返回一个新的数组。写一个函数将该数字按照大小进行排列，最大的数在列表的最前面。
    格式：
    输入行依次输入一个整数数组，最后输出排序后的数组。
    样例输入
    [ 1，2，3 ]
    [ 9，9，9 ]
    样例输出
    [ 1，2，4 ]
    [ 1，0，0，0 ]
 * @author: yjy
 * @datetime: 2017/12/28 11:36
 */
public class ArrayIncrease {

    public static void main(String[] s) {
        int[] a = {9, 9, 9 ,9};
        Arrays.stream(increase(a)).forEachOrdered(System.out::print);
    }

    /**
     * 数组自增
     * @param a 数组
     * @return 自增后数组
     */
    public static int[] increase(int[] a) {
        return increaseAt(a, a.length - 1);
    }

    /**
     * 指定位加1 > 时间复杂度 O(n)
     * @param a 数组
     * @param i 下标
     * @return 自增后数组
     */
    public static int[] increaseAt(int[] a, int i) {
        if (a[i] < 9) {
            a[i] ++;
            return a;
        } else {
            a[i] = 0;
            if (i > 0) {
                return increaseAt(a, --i);
            } else {
                int[] temp = new int[a.length + 1];
                temp[0] = 1;
                System.arraycopy(a, 0, temp, 1, a.length);
                return temp;
            }
        }
    }
}
