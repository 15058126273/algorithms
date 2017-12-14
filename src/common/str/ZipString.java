package common.str;

/**
 * 字符串压缩
 * @author yjy
 * @date 2017年4月5日下午5:45:34
 */
public class ZipString {

	public static String stringZip(String inString) {
		if (inString != null) {
			StringBuilder sb = new StringBuilder("");
			while (inString.length() > 0) {
				char s = inString.charAt(0);
				int count = 1;
				while (inString.length() > 1 && s == inString.charAt(1)) {
					inString = inString.substring(1);
					count ++;
				}
				if (count > 1) {
					sb.append(count);
				}
				sb.append(s);
				inString = inString.substring(1);
			}
			return sb.toString();
		}
		return inString;
	}
	
	public static void main(String[] at) {
		
		String inString = "cccddecc";
		System.out.println(stringZip(inString));
	}
}
