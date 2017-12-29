package common.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 组合
 * 给出两个整数 n 和 k，写一个函数返回从 1......n 中选出的 k 个数的组合。
    格式：
    输入行依次输入一个整数 n 和一个整数 k，最后输出所有 k 个数的组合。
    样例输入
    n = 4
    k = 2
    样例输出
    [ [ 2，4 ]，[ 3，4 ]，[ 2，3 ]，[ 1，2 ]，[ 1，3 ]，[ 1，4 ]]
 * @author: yjy
 * @datetime: 2017/12/29 16:14
 */
public class Combination1 {

    public static void main(String[] s) {
        int n = 7, k = 5;

        findCombination(n, k).forEach(l -> {
            Arrays.stream(l).forEachOrdered(System.out::print);
            System.out.println();
        });
    }

    public static List<int[]> findCombination(int n, int k) {
        int[] temp = new int[n];
        for (int i = 0; i < n; i++) {
            temp[i] = i + 1;
        }
        return recursion(temp, k);
    }

    private static List<int[]> recursion(int[] arr, int k) {
        int len = arr.length;
        List<int[]> res = new ArrayList<>();
        if (k == 1) {
            for (int x : arr) {
                res.add(new int[] {x});
            }
        } else if (k == len) {
            res.add(arr);
        } else {
            int[] temp = new int[len - 1];
            System.arraycopy(arr, 0, temp, 0, len - 1);
            List<int[]> res1 = recursion(temp, k - 1);
            for (int i = 0; i < res1.size(); i ++) {
                res1.set(i, addToArr(res1.get(i), arr[len - 1]));
            }
            List<int[]> res2 = recursion(temp, k);
            res1.addAll(res2);
            res.addAll(res1);
        }
        return res;
    }

    private static int[] addToArr(int[] arr, int x) {
        int[] temp = new int[arr.length + 1];
        System.arraycopy(arr, 0, temp, 0, arr.length);
        temp[arr.length] = x;
        return temp;
    }

}
