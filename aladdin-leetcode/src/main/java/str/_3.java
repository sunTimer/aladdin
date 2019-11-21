package str;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class _3 {

    public int lengthOfLongestSubstring(String s) {
        Set<Character> data = new HashSet<>();

        int i = 0, j = 0;
        int n = s.length();

        int ans = 0;

        while (i < n && j < n){
            if (!data.contains(s.charAt(j))) {
                data.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                data.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    @Test
    public void test() {
        Assert.assertEquals(3, lengthOfLongestSubstring("abc"));
        Assert.assertEquals(1, lengthOfLongestSubstring("bbbbb"));
    }
}
