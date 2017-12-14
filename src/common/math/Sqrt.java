package common.math;

public class Sqrt {
	
	public static void main(String[] args) {
		int a = 12;
		printCallStatck(a);
	}

	 public static void printCallStatck(int a) {
	    	int count = 0;
	    	long time1 = System.nanoTime();
	    	double c = 0;
	    	double t = 1e-15;
	    	while ( (c = Math.pow(t, 2)) < a - (1e-5) || c > a + (1e-5)) {
	    		t = (t + a/t) / 2;
//	    		count++;
//	    		System.out.println(t);
	    	}
	    	long time2 = System.nanoTime();
	    	double d = Math.sqrt(a);
	    	long time3 = System.nanoTime();
	    	System.out.println("time:" + (time2 - time1) + ",t:" +t);
	    	System.out.println("time:" + (time3 - time2) + ",t:" + d);
	    	
	    }
}
