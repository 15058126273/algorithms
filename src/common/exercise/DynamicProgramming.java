package common.exercise;

import java.util.Arrays;
import java.util.Set;

/**
 * @description: 动态规划
 * @author: yjy
 * @datetime: 2017/12/21 15:25
 */
public class DynamicProgramming {

    public static void main(String[] args) {
        int num = climbStairs(10, 3);
        System.out.println(num);


        Set<String> keys = System.getProperties().stringPropertyNames();
        for (String key : keys) {
            System.out.println(key + " > " + System.getProperty(key));
        }

    }

    /**
     * 爬楼梯问题
     * 题目: 有一座高度是10级台阶的楼梯，从下往上走，每跨一步只能向上1级或者2级台阶。要求用程序来求出一共有多少种走法。
         比如，每次走1级台阶，一共走10步，这是其中一种走法。我们可以简写成 1,1,1,1,1,1,1,1,1,1。
     * @param stairsCount 台阶数
     * @param maxStep 最大跨步
     * @return 走法个数
     */
    public static int climbStairs(int stairsCount, int maxStep) {
        if (stairsCount < 1) {
            return 0;
        }
        if (stairsCount <= maxStep) {
            return 1 + climbStairs(stairsCount - 1, maxStep);
        }

        int[] arr = new int[maxStep];
        for (int i = 0; i < maxStep; i++) {
            arr[i] = climbStairs(i + 1, maxStep);
        }
        int temp = 0;
        for (int i = maxStep + 1; i <= stairsCount; i++) {
            temp = Arrays.stream(arr).sum();
            arr = addToEnd(Arrays.copyOfRange(arr, 1, arr.length), temp);
        }
        return temp;
    }

    /**
     * 数组追加
     * @param arr 数组
     * @param i 追加元素
     * @return 修改后的数组
     */
    private static int[] addToEnd(int[] arr, int i) {
        int len = arr.length;
        int[] temp = new int[len + 1];
        System.arraycopy(arr, 0, temp, 0, len);
        temp[len] = i;
        return temp;
    }

}
