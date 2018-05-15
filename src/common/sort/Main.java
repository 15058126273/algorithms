package common.sort;

import common.util.TimeClocker;

/**
 * description
 *
 * @author yjy
 * @date 2018-05-09 15:42
 */
public class Main {

    public static void main(String[] args) {
        // just do it...
        int size = 50000000;
        Double[] doubles = generate(size);

        TimeClocker clocker = new TimeClocker();

//        // 选择排序
//        Selector.sort(doubles);

//        // 插入排序
//        Insertion.sort(doubles);

//        // 希尔排序
//        Shell.sort(doubles);

//        // 并归排序 自顶向下
//        MergeU2D.sort(doubles);

//        // 并归排序 自底向上
//        MergeD2U.sort(doubles);

//        // 快速排序
//        Quick.sort(doubles);

//        // 快速排序 三向切分
//        QuickThree.sort(doubles);

        // 堆排序
        Heap.sort(doubles);

        clocker.stop("排序");

        System.out.println("是否有序: " + isSorted(doubles));
    }

    // 判断是否有序
    public static boolean isSorted(Comparable[] arr) {
        for (int i = 0; i < arr.length - 2; i++) {
            if (arr[i].compareTo(arr[i + 1]) > 0) return false;
        }
        return true;
    }

    // 生成随机集合
    public static Double[] generate(int size) {
        Double[] doubles = new Double[size];
        while (size-- > 0) {
            doubles[size] = Math.random();
        }
        return doubles;
    }

}
