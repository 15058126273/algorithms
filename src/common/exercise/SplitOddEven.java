package common.exercise;

import java.util.Arrays;

/**
 * @description: 奇偶分割数组
 * 写一个函数分割一个整数数组，使得奇数在前偶数在后。
    挑战：
    在原数组中完成，不使用额外空间。
    格式：
    输入行输入一个整数数组，最后输出分割后的数组。
    样例输入
    [ 1， 2，3，4 ]
    样例输出
    [ 1，3，2，4 ]
 * @author: yjy
 * @datetime: 2017/12/29 15:16
 */
public class SplitOddEven {

    public static void main(String[] s) {
        int[] a = {2,3,1,6,8,5,7,9,4};
        splitOddEven(a);
        Arrays.stream(a).forEachOrdered(System.out::print);
    }

    /**
     * 奇偶分割
     * @param a 数组
     */
    public static void splitOddEven(int[] a) {
        main:
        for (int i = 0, l = a.length; i < l; i++) {
            // 偶数
            if ((a[i] & 1) == 0) {
                // 往后遍历
                for (int j = i + 1; j < l; j++) {
                    // if 遍历至最后一个奇数
                    if ((j == l - 1 && (a[j] & 1) == 1) || ((a[j] & 1) == 0 && (a[j - 1] & 1) == 1)) {
                        // 与最后一个奇数交换
                        a[i] += a[j - 1];
                        a[j - 1] = a[i] - a[j - 1];
                        a[i] = a[i] - a[j - 1];
                        continue main;
                    }
                }
                // 未找到奇数, 结束
                break;
            }
        }
    }
}
