package easy;

import org.junit.Assert;
import org.junit.Test;

public class _09 {

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        String s = Integer.toString(x);
        char first = s.charAt(0);
        if (first == '-' || first == '+') {
            return false;
        }
        int begin = 0;
        int end = s.length() - 1;
        while (begin < end) {
            if (s.charAt(begin++) == s.charAt(end--)) {
                continue;
            }
            return false;
        }
        return true;
    }

    public boolean isPalindrome2(int x) {
        x = 2345432;
        return true;
    }


    @Test
    public void test() {
        Assert.assertTrue(isPalindrome(121));
        Assert.assertFalse(isPalindrome(-123));
        Assert.assertFalse(isPalindrome(-121));
        Assert.assertTrue(isPalindrome(1221));
        Assert.assertFalse(isPalindrome(+1221));
        Assert.assertTrue(isPalindrome(124565421));
    }
}
