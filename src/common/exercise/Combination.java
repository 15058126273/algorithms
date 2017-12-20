package common.exercise;

import java.util.Arrays;

/**
 * @description: 数字组合(背包问题)
 * 给出一个候选数字的集合 C 和目标数字 T，写一个函数找到 C 中所有的组合，使找出的数字和为 T。C 中的数字可以无限制重复被选取。
    注意事项：
    1、所有的数字（包括目标数字）均为正整数。
    2、元素组合（a1, a2, … , ak）必须是非降序（ie, a1 ≤  a2  ≤ … ≤  ak）。
    3、解集不能包含重复的组合。
    格式：
    输入行每一行输入数组 C 和一个目标数字 T，最后输出所有满足条件的组合。
    样例输入
    C = [ 2，3，6，7 ]
    T = 7
    样例输出
    [ [ 7 ]，[ 2，2，3 ] ]
 * @author: yjy
 * @datetime: 2017/12/19 16:05
 */
public class Combination {

    public static void main(String[] args) {
        int[] arr = {1,2,4,1,5,6,8,9,10};
        int target = 8;
        findCombination(arr, target);
    }

    /**
     * 取所有组合集合
     * @param arr 给定正整数集合
     * @param target 目标正整数
     */
    public static void findCombination(int[] arr, int target) {
        // 数组去重 & 将大于目标值的元素移除
        arr = distinctArr(arr, target);
        if (arr == null || arr.length == 0) {
            System.out.print("无2");
            return;
        }

        // 排序
        Arrays.sort(arr);

        // 递归
        testReduce(arr, arr.length - 1, target, new int[0]);
    }

    /**
     * 递归
     * @param arr 集合
     * @param currI 当前下标
     * @param target 剩余值
     * @param comb 当前组合
     */
    private static void testReduce(int[] arr, int currI,  int target, int[] comb) {
        for (int i = currI; i >= 0; i-- ) {
            if (comb.length > 0 && i > comb[comb.length - 1]) {
                continue;
            }
            target -= arr[i];
            comb = pushToArr(comb, i);
            if (target > 0) {
                testReduce(arr, currI, target, comb);
                return;
            } else if (target == 0) {
                // 输出当前组合
                System.out.print("[");
                Arrays.stream(comb).forEachOrdered(e -> System.out.print(arr[e] + ","));
                System.out.println("],");

                // 检测是否可以回退一步换条路走
                int combL = comb.length;
                if (combL > 0) {
                    for (int z = combL - 1; z >= 0; z--) {
                        int zz = comb[z];
                        target += arr[zz];
                        comb = pollFromArr(comb);
                        if (zz > 0) {
                            currI = zz - 1;
                            testReduce(arr, currI, target, comb);
                            return;
                        }
                    }
                }
            } else {
                comb = pollFromArr(comb);
                target += arr[i];
            }

        }
    }

    /**
     * 数组追加
     * @param arr 数组
     * @param x 追加值
     * @return 新数组
     */
    private static int[] pushToArr(int[] arr, int x) {
//        System.out.println("pushToArr : " + x);
        int len = arr.length;
        int[] temp = new int[len + 1];
        System.arraycopy(arr, 0, temp, 0, len);
        temp[len] = x;
        return temp;
    }

    /**
     * 移除数组最后一个值
     * @param arr 数组
     * @return 新数组
     */
    private static int[] pollFromArr(int[] arr) {
//        System.out.println("pollFromArr : " + arr[arr.length - 1]);
        int len;
        if (arr != null && (len = arr.length) > 0) {
            int[] temp = new int[len - 1];
            System.arraycopy(arr, 0, temp, 0, len - 1);
            return temp;
        }
        return arr;
    }

    /**
     * 将一个组合添加至结果
     * @param res 结果集
     * @param comb 组合
     * @return 新结果集
     */
    private static int[][] addCombToRes(int[][] res, int[] comb) {
        int[][] tempRes = new int[res.length + 1][];
        System.arraycopy(res, 0, tempRes, 0, res.length);
        tempRes[tempRes.length - 1] = comb;
        return tempRes;
    }

    /**
     * 数组去重
     * @param arr 数组
     * @return 结果数组
     */
    private static int[] distinctArr(int[] arr, int maxValue) {
        int arrLen;
        if (arr == null || (arrLen = arr.length) == 0) {
            return arr;
        }
        int[] temp = new int[arrLen];
        int tempCount = 0;
        label1:
        for (int i = 0; i < arrLen; i++ ) {
            int a = arr[i];
            for (int x : temp) {
                if (x == a || a > maxValue) {
                    continue label1;
                }
            }
            temp[tempCount++] = arr[i];
        }
        arr = new int[tempCount];
        System.arraycopy(temp, 0, arr, 0, tempCount);
        return arr;
    }

}
