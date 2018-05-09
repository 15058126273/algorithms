package common.exercise;

import java.util.*;

/**
 * 花牌算法题2
 *
 * 一个整数数组, 数组长度小于50, 数值范围是 [0~10], 同一个数值重复次数不超过5次
 arr?=?[1,2,2,2,2,2,3,3,4,4,4,5,5,5,5,6,6,6,6,6,7,7,7,7,8,8,9,9,9,9,9]

 规定:?从数组中去除?4张一样的牌?可以获得1分,?去除5张一样的牌?可以获得?2?分

 问题:? 对于上面这个数组,?想要获得3分可以有几种去除方式?
 进阶问题:?能否将每种去除方式打印出来

 例子:
 arr?=?[1,2,2,3,3,3,3,4,4,4,4,4,5,5,5,5,6,6,7]
 答案:?3种
 进阶答案:?三种方式分别是:{?3333?4444?5555}??{3333?44444}??{44444?5555}

 *
 * @Author yjy
 * @Date 2018-04-09 13:36
 */
public class HuAlgorithm2 {

    public static void main(String[] args) {
        // 指定数组
//        int[] arr = {1,2,2,2,2,2,3,3,4,4,4,5,5,5,5,6,6,6,6,6,7,7,7,7,8,8,9,9,9,9,9};
        // 目标分数
        int score = 3;
        long start = System.currentTimeMillis();
        int[][] testArr = {
                {1,1,1,2,3,3,4,4,5,6,7,7,7,8,8,8,9,9,10},
                {1,1,1,1,1,2,3,3,3,3,3,4,4,4,4,4,5,5,5,5,5,6,6,6,6,7,7,7,8,8,8,8,8,9,9,9,9,9,10,10,10,10},
                {1,1,1,1,2,2,2,3,4,4,5,5,5,6,6,6,6,6,7,7,7,8,8,8,8,9,9,10,10,10,10},
                {1,2,3,3,4,4,6,7,8,9,9},
                {1,1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,6,6,6,6,7,7,7,7,8,8,9,9,9,9,10},
                {1,1,1,1,1,2,2,2,2,2,3,4,4,4,4,4,5,5,5,5,6,6,6,6,7,7,8,8,8,8,8,9,9,9,10,10},
                {1,1,4,4,4,6},
                {1,1,1,1,2,2,3,3,3,4,4,4,4,4,5,6,6,6,6,7,7,7,7,7,8,8,8,8,8,9,9,9,10,10,10},
                {3,4,5,5,5,6,6,7},
                {1,2,2,2,2,2,3,3,4,4,5,5,6,6,7,7,8,8,8,9,9,10,10,10,10,10},
                {1,1,1,2,2,2,2,3,3,4,4,4,5,5,5,5,5,6,6,6,6,6,7,7,8,8,9,10,10,10,10,10},
                {1,3,3,4,5,5,6,6,10},
                {1},
                {1,1,1,1,1,2,2,2,2,2,3,3,4,4,4,4,4,6,6,6,6,6,7,7,7,7,8,8,8,8,8,9,9,9,10,10,10},
                {1,1,1,1,1,2,2,2,2,3,3,3,3,3,4,4,4,4,4,5,5,5,5,5,6,6,6,6,6,7,7,7,7,7,8,8,9,9,10,10,10,10,10},
                {2,3,5},
                {3,3,3,3,6,7,7},
                {1,1,2,3,3,3,4,5,5,6,6,6,7,7,7,7,7,9,9,9,10,10,10},
                {1,1,1,1,2,2,2,3,3,3,3,4,4,4,4,4,5,5,6,6,6,6,6,7,7,8,8,8,8,8,9,9,9,9,10},
                {2,3,3,6,7,9}
        };
        for(int i = 0;i < testArr.length;i++) {
            int[] arr = testArr[i];
            // 解法1
            List<Map<Integer, List<Integer>>> res = Solution1.solution(arr, score);
            // 打印结果
            printResult(res);
        }
        System.out.println("总耗时：" + (System.currentTimeMillis() - start) + "ms");
    }


    /**
     * 解法1
     * 思路:
     * 1.先统计每个数字出现的次数
     * 2.将出现5次和4次的数字分别记入对应列表
     * 3.根据两个列表长度判断分数是否能达到
     * 4.能达到的情况下,根据指定分数计算至少要取 minUse5 个5位数, 以及最多能取 maxUse5 个5位数
     * 5.循环(取 use5 个5位数, minUse5 <= use5 <= maxUse5)
     *      根据 score 和 use5 计算需要取 use4 个四位数
     *      排列组合从5位数列表中取 use5 个5位数
     *      循环(所有 use5 个5位数的组合方式)
     *          利用排列组合取 use4 个四位数
     *          整合为一个组合方式存入map并加入到结果集中
     * 6.最终得到一个所有组合方式的结果集
     */
    public static class Solution1 {

        /**
         * 算法入口
         * @param arr 原数组
         * @param score 目标分数
         * @return 所有获得目标分数的方式
         */
        public static List<Map<Integer, List<Integer>>> solution(int[] arr, int score) {
            logTime("solution1");
            List<Map<Integer, List<Integer>>> res = new ArrayList<>();
            // 统计每个数值出现的次数
            int[] count = count(arr);
            // 提取重复5次 和 4次的数值
            List<Integer> repeat5 = extractPointCountNum(count, 5);
            List<Integer> repeat4 = extractPointCountNum(count, 4);
            logTime("solution2");
            // if 确保有解
            int tempNum = score - repeat4.size();
            if (tempNum <= 0 || (tempNum >> 1) < repeat5.size()) {
                res.addAll(shenmegui(repeat5, repeat4, score));
            }
            logTime("solution3");
            return res;
        }

        /**
         * 统计每个数值出现的次数
         * @param arr 原数组
         * @return 统计次数数组 下标为数组, 值为出现次数
         */
        private static int[] count(int[] arr) {
            logTime("count1");
            int[] count = new int[22];
            for (int x : arr) {
                count[x] ++;
            }
            logTime("count2");
            return count;
        }

        /**
         * 提取重复指定次数的数值
         * @param count 统计数组
         * @param repeatCount 指定出现次数
         * @return 出现指定次数的数值集合
         */
        private static List<Integer> extractPointCountNum(int[] count, int repeatCount) {
            logTime("extractPointCountNum1");
            List<Integer> list = new LinkedList<>();
            for (int i = 0; i < count.length; i++) {
                if (count[i] == repeatCount) {
                    list.add(i);
                }
            }
            logTime("extractPointCountNum2");
            return list;
        }

        /**
         * 神秘的核心算法
         * @param repeat5 重复5次的数值
         * @param repeat4 重复4次的数值
         * @param score 分数
         * @return 各种组合方法
         */
        private static List<Map<Integer, List<Integer>>> shenmegui(List<Integer> repeat5, List<Integer> repeat4, int score) {
            logTime("shenmegui1");
            List<Map<Integer, List<Integer>>> res = new ArrayList<>();
            Map<Integer, List<Integer>> map;
            // 最多可以去除 maxUse5 个 repeat5
            int maxUse5 = Math.min(repeat5.size(), score >> 1);
            // 最少要去除 minUse5 个 repeat5
            int minUse5 = Math.max(0, score - (repeat4.size() + repeat5.size()));
            logTime("shenmegui2");
            for (int use5 = minUse5; use5 <= maxUse5; use5++) {
                // 需要去除 use4 个 repeat4
                int use4 = score - (use5 << 1);
                if (use5 > 0) {
                    logTime("shenmegui21");
                    // 先提取 repeat5
                    List<List<Integer>> list5 = combination(repeat5, use5);
                    logTime("shenmegui22");
                    for (List<Integer> that5 : list5) {
                        logTime("shenmegui23");
                        if (use4 > 0) {
                            List<Integer> waitRepeat4 = new LinkedList<>(repeat4);
                            for (int x : repeat5) {
                                if (!that5.contains(x)) waitRepeat4.add(x);
                            }
                            List<List<Integer>> list4 = combination(waitRepeat4, use4);
                            logTime("shenmegui24");
                            for (List<Integer> that4 : list4) {
                                map = new HashMap<>();
                                map.put(4, that4);
                                map.put(5, that5);
                                res.add(map);
                            }
                            logTime("shenmegui25");
                        } else {
                            logTime("shenmegui26");
                            map = new HashMap<>();
                            map.put(5, that5);
                            map.put(4, new ArrayList<>());
                            res.add(map);
                            logTime("shenmegui27");
                        }
                    }
                }
                // else 直接提取 repeat4
                else {
                    logTime("shenmegui28");
                    List<Integer> waitRepeat4 = new ArrayList<>(repeat4);
                    waitRepeat4.addAll(repeat5);
                    List<List<Integer>> list4 = combination(waitRepeat4, use4);
                    logTime("shenmegui29");
                    for (List<Integer> that4 : list4) {
                        map = new HashMap<>();
                        map.put(5, new ArrayList<>());
                        map.put(4, that4);
                        res.add(map);
                    }
                    logTime("shenmegui291");
                }
            }
            logTime("shenmegui3");
            return res;
        }

        /**
         * 排列组合
         * @param repeatList 待排列列表
         * @param count 组合数
         * @return 排列组合结果
         */
        private static List<List<Integer>> combination(List<Integer> repeatList, int count) {
            logTime("combination1");
            assert repeatList != null && repeatList.size() > 0: "repeatList is empty";
            assert count > 0: "count:" + count + " <= 0";
            List<List<Integer>> res = new LinkedList<>();
            logTime("combination2");
            if (count == 1) {
                for (Integer n : repeatList) {
                    res.add(new ArrayList<>(Collections.singletonList(n)));
                }
                logTime("combination21");
            } else {
                logTime("combination3");
                List<Integer> nextRepeatList;
                for (int i = 0, l = repeatList.size(); i < l; i++) {
                    if (i < l - 1) {
                        nextRepeatList = new ArrayList<>(repeatList.subList(i + 1, l));
                        for (List<Integer> l2 : combination(nextRepeatList, count - 1)) {
                            l2.add(repeatList.get(i));
                            res.add(l2);
                        }
                    }
                }
                logTime("combination4");
            }
            logTime("combination5");
            return res;
        }
    }

    /**
     * 打印结果
     * @param res 结果
     */
    private static void printResult(List<Map<Integer, List<Integer>>> res) {
        if (res != null && !res.isEmpty()) {
            System.out.println("有 " + res.size() + " 个解");
            for (Map<Integer, List<Integer>> map : res) {
                System.out.print(map.get(5) + "*5  ");
                System.out.print(map.get(4) + "*4  ");
                System.out.println();
            }
        } else {
            System.out.println("无解");
        }
    }

    /**
     * 打印当时时间
     * @param name 前缀
     */
    private static void logTime(String name) {
        System.out.println(name + ":" + System.currentTimeMillis());
    }

}