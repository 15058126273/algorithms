package common.test;

public class Test {

	public static void main(String[]args) {
		String a = "12345";
		String b = "1234" + "5";
		String c = "1234".concat("5");
		String d = "1234512345";
		String e = a + a;
		System.out.println((a == b) + ":" + (a.equals(b)));
		System.out.println((a == c) + ":" + (a.equals(c)));
		System.out.println((b == c) + ":" + (b.equals(c)));
		System.out.println((d == e) + ":" + (d.equals(e)));
		
	}
	
}
