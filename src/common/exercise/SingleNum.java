package common.exercise;

import java.util.HashSet;
import java.util.Set;

/**
 * @description: 落单的数
 * 给出 2*n + 1 个的数字，除其中一个数字之外其他每个数字均出现两次，写一个函数找到这个数字。
    挑战：
    一次遍历，常数级的额外空间复杂度
    格式：
    输入行输入一个数组，最后输出出现一次的数字。
    样例输入
    [ 1，2，2，1，3，4，3 ]
    样例输出
    4
 * @author: yjy
 * @datetime: 2017/12/21 10:46
 */
public class SingleNum {

    public static void main(String[] rags) {
        int[] arr = {1,2,2,5,1,3,4,3,5};
        System.out.println(findSingleUseXor(arr));
        System.out.println(findSingleUseHash(arr));
    }

    /**
     * 找出落单的数字 (异或大法)
     * @param arr 数组
     * @return 数字
     */
    public static int findSingleUseXor(int[] arr) {
        int i = 0;
        for (int x : arr) {
            i ^= x;
        }
        return i;
    }

    /**
     * 找出落单的数字 (hash大法)
     * @param arr 数组
     * @return 数字
     */
    public static int findSingleUseHash(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int x : arr) {
            if (set.contains(x)) {
                set.remove(x);
            } else {
                set.add(x);
            }
        }
        return (Integer)set.toArray()[0];
    }

}
