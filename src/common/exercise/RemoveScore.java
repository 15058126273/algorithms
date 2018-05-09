package common.exercise;

import java.lang.reflect.Array;
import java.util.*;


public class RemoveScore {

    // generateTestArr生成的测试数据
    public static int[][] testArr = {
            {1, 1, 1, 2, 3, 3, 4, 4, 5, 6, 7, 7, 7, 8, 8, 8, 9, 9, 10},
            {1, 1, 1, 1, 1, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 10, 10, 10, 10},
            {1, 1, 1, 1, 2, 2, 2, 3, 4, 4, 5, 5, 5, 6, 6, 6, 6, 6, 7, 7, 7, 8, 8, 8, 8, 9, 9, 10, 10, 10, 10},
            {1, 2, 3, 3, 4, 4, 6, 7, 8, 9, 9},
            {1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 9, 9, 9, 9, 10},
            {1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 8, 8, 8, 8, 8, 9, 9, 9, 10, 10},
            {1, 1, 4, 4, 4, 6},
            {1, 1, 1, 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4, 5, 6, 6, 6, 6, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 9, 9, 9, 10, 10, 10},
            {3, 4, 5, 5, 5, 6, 6, 7},
            {1, 2, 2, 2, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 8, 9, 9, 10, 10, 10, 10, 10},
            {1, 1, 1, 2, 2, 2, 2, 3, 3, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 7, 7, 8, 8, 9, 10, 10, 10, 10, 10},
            {1, 3, 3, 4, 5, 5, 6, 6, 10},
            {1},
            {1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 4, 6, 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8, 8, 9, 9, 9, 10, 10, 10},
            {1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 8, 8, 9, 9, 10, 10, 10, 10, 10},
            {2, 3, 5},
            {3, 3, 3, 3, 6, 7, 7},
            {1, 1, 2, 3, 3, 3, 4, 5, 5, 6, 6, 6, 7, 7, 7, 7, 7, 9, 9, 9, 10, 10, 10},
            {1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 6, 6, 6, 6, 6, 7, 7, 8, 8, 8, 8, 8, 9, 9, 9, 9, 10},
            {2, 3, 3, 6, 7, 9}
    };

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0, len = testArr.length; i < len; i++) {
            int[] arr = testArr[i];
            System.out.println("操作数组：" + Arrays.toString(arr));
            // 统计所有元素的个数
            Map<Integer, Integer> elementCount = count(arr);
            // 两个阵营- 4个/5个
            int[][] camps = camp(elementCount);
            // 4或5阵营中取三个 或4阵营中取1个+5阵营中取1   或取1个5当4用1个5当5用
            int total = scoreType(camps);
            System.out.println("一共有" + total + "种消除方法");
            // 打印每种方法
            print(camps);
            System.out.println();
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + "ms");
    }

    public static void print(int[][] camps) {
        int[] camp4 = camps[0];
        int[] camp5 = camps[1];
        // 4或5阵营中取3
        int[] total = Arrays.copyOf(camp4, camp4.length + camp5.length);
        System.arraycopy(camp5, 0, total, camp4.length, camp5.length);
        if (total.length >= 3) {
            combine(0, 3, total);
        }
        // 5阵营取1 4阵营取1
        if (camp4.length > 0 && camp5.length > 0) {
            print2Loop(camp4, camp5);
        }
        // 5做4取1 5取1
        if (camp5.length >= 2) {
            print2Loop(camp5, camp5);
        }
    }

    public static void print2Loop(int[] arrFor4, int[] arrFor5) {
        boolean isSame = arrFor4.equals(arrFor5);
        for (int i = 0; i < arrFor4.length; i++) {
            for (int j = 0; j < arrFor5.length; j++) {
                if (isSame && i == j) {
                    continue;
                }
                System.out.print("{");
                printTimes(arrFor4[i], 4);
                System.out.print(" ");
                printTimes(arrFor5[j], 5);
                System.out.print("}");
                System.out.println();
            }
        }
    }

    public static int[] removeOneElement(int[] array, int index) {
        int length = array != null ? array.length : 0;
        if (index >= 0 && index < length) {
            int[] result = (int[])Array.newInstance(array.getClass().getComponentType(), length - 1);
            System.arraycopy(array, 0, result, 0, index);
            if (index < length - 1) {
                System.arraycopy(array, index + 1, result, index, length - index - 1);
            }

            return result;
        } else {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);
        }
    }

    public static void printTimes(int a, int times) {
        for (int i = 0; i < times; i++) {
            System.out.print(a);
        }
    }

    public static void printCamp4(ArrayList<Integer> camp4) {
        System.out.print("{");
        for (Integer item : camp4) {
            printTimes(item, 4);
            System.out.print(" ");
        }
        System.out.print("}");
        System.out.println();
    }

    public static int scoreType(int[][] camps) {
        int camp4Count = camps[0].length;
        int camp5Count = camps[1].length;
        // 4或5阵营中取3
        int campTotal = camp4Count + camp5Count;
        int num1 = campTotal < 3 ? 0 : (campTotal == 3 ? 1 : factorial(campTotal) / (6 * factorial(campTotal - 3)));

        // 5阵营取1 4阵营取1
        int num2 = camp4Count > 0 && camp5Count > 0 ? camp4Count * camp5Count : 0;

        // 5做4取1 5做5取1
        int num3 = camp5Count < 2 ? 0 : (camp5Count - 1) * camp5Count;
        return num1 + num2 + num3;
    }

    //利用递归计算阶乘
    public static int factorial(int num) {
        int sum = 1;
        if (num < 0)
            throw new IllegalArgumentException("必须为正整数!");//抛出不合理参数异常
        if (num == 1) {
            return 1;//根据条件,跳出循环
        } else {
            sum = num * factorial(num - 1);//运用递归计算
            return sum;
        }
    }

    public static Map<Integer, Integer> count(int[] array) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            Integer count = map.get(array[i]);
            map.put(array[i], count == null ? 1 : count + 1);
        }
        return map;
    }

    public static int[][] camp(Map<Integer, Integer> map) {
        if (null == map) {
            return null;
        }
        List<Integer> campFour = new ArrayList<>();
        List<Integer> campFive = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue().intValue() == 4) {
                campFour.add(entry.getKey());
            }
            if (entry.getValue().intValue() == 5) {
                campFive.add(entry.getKey());
            }
        }
        int[][] res = {campFour.stream().mapToInt(j -> j).toArray(),
                campFive.stream().mapToInt(j -> j).toArray()};
        return res;
    }

    private static ArrayList<Integer> tmpArr = new ArrayList<>();

    /**
     * 组合算法
     * 按一定的顺序取出元素，就是组合,元素个数[C arr.len 3]
     *
     * @param index 元素位置
     * @param k     选取的元素个数
     * @param arr   数组
     */
    public static void combine(int index, int k, int[] arr) {
        if (k == 1) {
            for (int i = index; i < arr.length; i++) {
                tmpArr.add(arr[i]);
                // 打印
                printCamp4(tmpArr);
                tmpArr.remove((Object) arr[i]);
            }
        } else if (k > 1) {
            for (int i = index; i <= arr.length - k; i++) {
                tmpArr.add(arr[i]); //tmpArr都是临时性存储一下
                combine(i + 1, k - 1, arr); //索引右移，内部循环，自然排除已经选择的元素
                tmpArr.remove((Object) arr[i]); //tmpArr因为是临时存储的，上一个组合找出后就该释放空间，存储下一个元素继续拼接组合了
            }
        } else {
            return;
        }
    }

}

