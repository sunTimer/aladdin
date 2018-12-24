package dp;

import java.util.ArrayList;
import java.util.List;

/**
 * 切钢筋
 * 有一根长度为l的钢筋，i为钢材长度，p为该长度下的收益
 * 问:有一根长度为l的钢材，如何分割，实现收益最大
 * ---------------------------
 * i | 1 | 2 | 3 | 4 |...
 * p | 1 | 4 | 5 | 10 |..
 */

public class CutRod {


    /**
     * @param p 收益表
     * @param l 钢材长度
     * @return
     */
    public static int maxProfit(int[] p, int l) {
        int[] r = new int[l + 1];
        int[] s = new int[l + 1];
        r[0] = 0;

        for (int i = 1; i <= l; i++) {
            int q = r[i];
            int old = q;
            for (int j = 1; j <= i; j++) {
                q = Math.max(q, p[i]);
                q = Math.max(q, p[j] + r[i - j]);
                if (old != q) {
                    s[j] = i;
                }
            }
            r[i] = q;
        }
        print_path(s, l);
        return r[l];
    }


    public static void print_path(int[] s, int n) {
        List<Integer> path = new ArrayList<Integer>();
        while (n > 0) {
            path.add(s[n]);
            n = n - s[n];
        }
        System.out.println(path);
    }

    public static void main(String[] args) {
        int[] p = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        for (int i = 0; i < 10; i++) {
            System.out.println(maxProfit(p, i));
        }
    }
}
