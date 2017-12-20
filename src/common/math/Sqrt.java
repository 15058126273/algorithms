package common.math;

public class Sqrt {

    public static void main(String[] args) {
        int a = 12;
        sqrt(a);
    }

    public static void sqrt(int a) {
        long time0 = System.nanoTime();
        long time1 = System.nanoTime();
        double c;
        double t = 1e-15;
        while ((c = Math.pow(t, 2)) < a - (1e-5) || c > a + (1e-5)) {
            t = (t + a / t) / 2;
        }
        long time2 = System.nanoTime();
        double d = Math.sqrt(a);

        long time3 = System.nanoTime();
        System.out.println("time:" + (time1 - time0));
        System.out.println("time:" + (time2 - time1) + ",t:" + t);
        System.out.println("time:" + (time3 - time2) + ",t:" + d);

    }
}
