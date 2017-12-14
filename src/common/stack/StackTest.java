package common.stack;

import java.util.Stack;

/**
 * 栈操作
 * @author yjy
 * @date 2017年4月6日下午1:09:01
 */
public class StackTest {

	private static Stack<Object> st = new Stack<Object>();
	
	public static void main(String[] args) {
		st.push(1);
		st.push(false);
		st.push("3");
		st.push('a');
		st.push(null);
		st.push(null);
		st.push(null);
		st.push(null);
		st.push(null);
		st.push(null);
		st.push(null);
		st.push(null);
		st.push(null);
		
		System.out.println(st + " -> size:" + st.size() + "-> isEmpty:" + st.isEmpty());
//		System.out.println(st.pop());
		System.out.println(st.peek());
//		System.out.println(st.add(new Date()));
		
//		System.out.println(st.addAll(new ArrayList<>()));
		System.out.println(st.capacity());
		System.out.println(st.clone());
		System.out.println(st.elementAt(0));
		
		System.out.println(st.get(0));
		System.out.println(st.firstElement());
		System.out.println(st.lastElement());
//		Object o = st.firstElement();
//		System.out.println(st.removeIf(st -> a == 1));
//		System.out.println(st.removeIf(st -> o != null));
//		Collection col = new ArrayList<Object>();
//		col.add(1);
//		col.add('a');
//		System.out.println(st.retainAll(col));
		
//		System.out.println(st.search("3"));
//		Enumeration<Object> en = st.elements();
//		while (en.hasMoreElements()) {
//			System.out.println(en.nextElement());
//		}

//		ListIterator li = st.listIterator();
//		while (li.hasNext()) {
//			System.out.println(li.next());
//		}
		
		System.out.println(st + " -> size:" + st.size() + "-> isEmpty:" + st.isEmpty());
	}
	
}
