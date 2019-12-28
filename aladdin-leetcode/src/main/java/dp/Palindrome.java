package dp;

import org.junit.Test;

/**
 * 最长回文字符串
 */
public class Palindrome {

    /**
     * 传统方法实现，已中间字母往两边扩散
     *
     * @param s 输入字符串
     * @return 最长回文子串
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        char[] chars = new char[s.length() * 2 - 1];
        for (int i = 0; i < chars.length; i++) {
            if (i % 2 == 1) {
                chars[i] = '#';
            } else {
                chars[i] = s.charAt(i / 2);
            }
        }

        String maxSubStr = "";
        for (int i = 1; i < chars.length; i++) {
            StringBuilder tmp;
            if (chars[i] == '#') {
                tmp = new StringBuilder();
            } else {
                tmp = new StringBuilder(chars[i] + "");
            }
            int left = 1, right;
            if (i < chars.length / 2) {
                right = i;
            } else {
                right = chars.length - 1 - i;
            }

            for (int j = left; j <= right; j++) {
                if (chars[i - j] == chars[i + j]) {
                    if (chars[i - j] == '#') {
                        continue;
                    }
                    tmp = new StringBuilder(chars[i - j] + tmp.toString() + chars[i - j]);
                } else {
                    break;
                }
            }
            if (tmp.length() >= maxSubStr.length()) {
                maxSubStr = tmp.toString();
            }
        }
        return maxSubStr;
    }


    /**
     * 动态规划实现
     *
     * @param s 字符串
     * @return 最长回文串
     */
    public String dp(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        boolean[][] p = new boolean[s.length()][s.length()];

        int minus = 0;
        String res = s.substring(0, 1);

        for (int i = 0; i < p.length; i++) {
            p[i][i] = true;
        }

        for (int i = 0; i < p.length - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                p[i][i + 1] = true;
                res = s.substring(i, i + 2);
            }
        }


        for (int j = 2; j < s.length(); j++) {
            for (int i = 0; i < s.length() - j; i++) {
                int left = i;
                int right = i + j;
                p[left][right] = s.charAt(left) == s.charAt(right) && p[left + 1][right - 1];
                if (p[left][right] && right - left > minus) {
                    minus = right - left;
                    res = s.substring(left, right + 1);
                }
            }
        }
        return res;
    }

    private void printArray(boolean[][] a) {
        for (boolean[] anA : a) {
            for (boolean anAnA : anA) {
                System.out.printf("%-10s", anAnA);
            }
            System.out.println();
        }
    }

    @Test
    public void test() {
        System.out.println(longestPalindrome("cbbd"));
        System.out.println(dp("cbbd"));
    }

}
