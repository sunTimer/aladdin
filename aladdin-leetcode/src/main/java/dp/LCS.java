package dp;

import java.util.Arrays;

/**
 * 最长公共子序列
 */
public class LCS {

    public static int lcs(String s1, String s2) {
        int r = s2.length();
        int c = s1.length();
        int[][] R = new int[r + 1][c + 1];
        for (int i = 0; i < r; i++) {

            for (int j = 0; j < c; j++) {
                if (s2.charAt(i) == s1.charAt(j)) {
                    R[i + 1][j + 1] = R[i][j] + 1;
                    System.out.print(s1.charAt(j));
                } else {
                    R[i + 1][j + 1] = max(R[i + 1][j], R[i][j + 1]);
                }
//                int max = max(R[i][j], R[i + 1][j], R[i][j + 1]);
//                if (s2.charAt(i) == s1.charAt(j)) {
//                    R[i + 1][j + 1] = max + 1;
//                } else {
//                    R[i + 1][j + 1] = max;
//                }
            }
        }
        printLcs(s1, s2, R[0].length - 1, R.length - 1, R);
        return R[r][c];
    }

    public static void main(String[] args) {
        System.out.println(lcs("bdcaba", "abcbdab"));
    }

    public static int max(int... param) {
        Arrays.sort(param);
        return param[param.length - 1];
    }

    public static void printArray(int[][] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = 1; j < a[i].length; j++) {
                System.out.printf("%5d", a[i][j]);
            }
            System.out.println();
        }
    }

    public static void printLcs(String s1, String s2, int i, int j, int[][] c) {
        if (s1.charAt(i) == s2.charAt(j)) {
            printLcs(s1, s2, i - 1, j - 1, c);
            System.out.print(s1.charAt(i));
        } else {
            if (c[i][j] == c[i - 1][j]) {
                printLcs(s1, s2, i - 1, j, c);
            } else {
                printLcs(s1, s2, i, j - 1, c);
            }
        }
    }

}
