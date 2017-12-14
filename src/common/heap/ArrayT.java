package common.heap;

import java.util.Arrays;
import java.util.List;

public class ArrayT {
	
	public static void main(String[] args) {
		String[] a = {"1", "2", "3", "4"};
		List<String> list = ggg(a);
		System.out.println(list.toString());
	}
	
	private static <C> List<C> ggg(C[] c) {
		
		System.out.println(c.getClass().getName());
		return Arrays.asList(c);
	}
}
