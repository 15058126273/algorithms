package common.exercise;

/**
 * @description: 字符串转整数
 * 实现atoi这个函数，将一个字符串转换为整数。如果没有合法的整数，返回0。
 * 若整数超出了32位整数的范围，如果是正整数则返回 INT_MAX（2147483647），或者如果是负整数则返回 INT_MIN（-2147483648）。
    格式：
    输入行每一行输入一个字符串，最后每一行输出经过转换后得到的整数。
    样例输入
    "10"
    "-1"
    "123123123123123"
    "1.0"
    样例输出
    10
    -1
    2147483647
    1
 * @author: yjy
 * @datetime: 2017/12/15 14:49
 */
public class StringToInt {

    public static void main(String[] args) {
        String a = "-1215514";
        System.out.println(atoi(a));
    }

    /**
     * 将字符串转为整数
     * @param a 字符串
     * @return 整数
     */
    public static int atoi(String a) {
        int count = 0, len;
        if (a == null || (len = a.length()) == 0) {
            return count;
        }
        char[] chars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        char ac;

        for (int i = len - 1, pCount = 0, lastP = len; i >= 0; i --) {
            if ((ac = a.charAt(i)) == '-') {
                if (i == 0) {
                    return count * -1;
                } else {
                    return 0;
                }
            } else if (ac == '.') {
                if (++pCount > 1 || count != 0) {
                    return 0;
                } else {
                    lastP = i;
                    continue;
                }
            }
            boolean matched = false;
            for (int c = 0; c < chars.length; c ++) {
                if ((matched = chars[c] == ac)) {
                   count += Math.pow(10, lastP - i - 1) * c;
                   break;
                }
            }
            if (!matched) {
                return 0;
            }
        }
        return count;
    }

}
