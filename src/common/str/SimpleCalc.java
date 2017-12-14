package common.str;

/**
 * 计算输入的数学表达式
 * @author yjy
 * @date 2017年4月5日下午5:45:34
 */
public class SimpleCalc {

	public static String calcString(String inString) {
		try {
			if (inString != null) {
				String[] params = inString.split(" "); 
				int num1 = Integer.valueOf(params[0]).intValue();
				String c = params[1];
				int num2 = Integer.valueOf(params[2]).intValue();
				if ("+".equals(c)) {
					return String.valueOf(num1 + num2);
				} else if ("-".equals(c)) {
					return String.valueOf(num1 - num2);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "0";
	}
	
	public static void main(String[] at) {
		String inString = "1 -2 2";
		System.out.println(calcString(inString));
	}
}
