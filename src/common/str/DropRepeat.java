package common.str;

/**
 * 字符串去重
 * @author yjy
 * @date 2017年4月5日下午5:45:34
 */
public class DropRepeat {

	/**
	 * 字符串去重
	 * @author yjy
	 * @date 2017年4月5日下午5:50:06
	 * @param inString
	 * @return
	 */
	public static String stringFilter(String inString) {
		if (inString != null) {
			char c;
			StringBuilder sb = new StringBuilder("");
			while (inString.length() > 0) {
				sb.append((c = inString.charAt(0)));
				inString = inString.replace(String.valueOf(c), "");
			}
			return sb.toString();
		}
		return inString;
	}
	
	public static void main(String[] at) {
		
		String inString = "asfshdshdssdf";
		System.out.println(stringFilter(inString));
	}
}
