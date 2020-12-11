package alg;

import org.junit.Test;

public class _69 {

    @Test
    public void test(){
        int k = 2000070000;
        System.out.println(mySqrt2(k));
        System.out.println("======");
        mySqrt(k);
    }

    public int mySqrt2(int k) {

        if (k == 0 || k == 1){
            return k;
        }

        double xi = k;

        while (true) {

            double yi = xi * xi - k;

            // y = ax + b
            double a = 2 * xi;

            double b = yi - a * xi;

            // y = 0
            double n = (0 - b) / a;

            if (Math.abs(n * n - k) < 0.01) {
                return (int) n;
            }
            System.out.println(n);
            xi = n;
        }
    }

    public int mySqrt(int x) {
        double left = 0;
        double right = x;

        while (true) {
            double mid = (left + right) / (double)2;
            double tmp = mid * mid;
            System.out.println(mid);
            if (Math.abs(tmp - x) < 0.01) {
                return (int) mid;
            } else {
                if (tmp > x) {
                    right = mid;
                } else {
                    left = mid;
                }
            }
        }
    }
}
