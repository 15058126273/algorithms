package common.exercise;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 乱序字符串
 * 给出一个字符串数组 S，写一个函数找到其中所有的乱序字符串（Anagram）。如果一个字符串是乱序字符串，那么它存在一个字母集合相同，但顺序不同的字符串也在S中。
    注意事项：
    所有的字符串都只包含小写字母
    格式：
    输入行输入一个字符数组 S，最后输出其中的乱序字符串。
    样例输入
    S = [ "lint"，"intl"，"inlt"，"code" ]
    样例输出
    [ "lint"，"inlt"，"intl" ]
 * @author: yjy
 * @datetime: 2017/12/20 14:29
 */
public class Anagram {

    public static void main(String[] s) {
        String[] arr = {"lint", "intl", "bca", "inlt", "code", "abc"};
//        String[] messArr = anagramUseLoop(arr);
        String[] messArr = anagramUseACSII(arr);
        Arrays.stream(messArr).forEach(System.out::println);
    }

    /**
     * 获取乱序字符串集合 (排序法)
     * @param arr 原集合
     * @return 乱序字符串集合
     */
    public static String[] anagramUseLoop(String[] arr) {
        int len = arr.length;
        String[] temp = new String[len];
        char[] chars;
        // 遍历数组排序字符串
        for (int i = 0; i < len; i ++) {
            chars = arr[i].toCharArray();
            Arrays.sort(chars);
            temp[i] = String.valueOf(chars);
        }
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            String x = temp[i];
            for (int j = i + 1, count = 0; j < len; j++) {
                if (x.equals(temp[j]) && !map.containsKey(j)) {
                    if (count++ == 0) {
                        map.put(i, arr[i]);
                    }
                    map.put(j, arr[j]);
                }
            }
        }
        return map.values().toArray(new String[0]);
    }

    /**
     * 获取乱序字符串集合 (Acsii求和 求积比较)
     * @param arr 原字符集合
     * @return 乱序集合
     */
    public static String[] anagramUseACSII(String[] arr) {
        int len = arr.length;
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            String x = arr[i];
            int sum = sumOfChars(x.toCharArray()), pro = proOfChars(x.toCharArray());
            for (int j = i + 1, count = 0; j < len; j++) {
                String y = arr[j];
                // if 字符串长度相同 && 和相同 && 积相同
                if (x.length() == y.length()
                        && sum == sumOfChars(y.toCharArray())
                        && pro == proOfChars(y.toCharArray())
                        && !map.containsKey(j)) {
                    if (count++ == 0) {
                        map.put(i, arr[i]);
                    }
                    map.put(j, arr[j]);
                }
            }
        }
        return map.values().toArray(new String[0]);
    }

    /**
     * 求和
     * @param chars 字符
     * @return 和
     */
    private static int sumOfChars(char[] chars) {
        int sum = 0;
        for (char c : chars) {
            sum += c;
        }
        return sum;
    }

    /**
     * 求积
     * @param chars 字符
     * @return 积
     */
    private static int proOfChars(char[] chars) {
        int product = 1;
        for (char c : chars) {
            product *= c;
        }
        return product;
    }

}
