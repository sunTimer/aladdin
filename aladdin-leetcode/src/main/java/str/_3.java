package str;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class _3 {

    @Test
    public void test2() {
        Assert.assertEquals(2, lengthOfLongestSubString("abba"));
        Assert.assertEquals(4, lengthOfLongestSubString("aabadcd"));
    }

    public int lengthOfLongestSubstring2(String s) {
        int left = 0;
        int right = 0;
        Map<Character, Integer> map = new HashMap<>(s.length());

        int max = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            if (map.containsKey(c)) {
                int p = map.get(c);
                left = Math.max(left, p + 1);
            }
            max = Math.max(max, right - left + 1);
            map.put(c, right);
            right++;
        }
        return max;
    }

    public int lengthOfLongestSubString(String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() <= 1) {
            return s.length();
        }

        char[] chars = s.toCharArray();
        int[] count = new int[256];
        Arrays.fill(count, -1);

        int max = 0;
        int curr = 0;

        for (int i = 0; i < chars.length; i++) {
            if (count[chars[i]] == -1) {
                curr++;
            } else {
                curr = i - count[chars[i]];
            }
            count[chars[i]] = i;
            max = Math.max(max, curr);
        }
        return max;
    }

    public int lengthOfLongestSubstring(String s) {
        Set<Character> data = new HashSet<>();

        int i = 0, j = 0;
        int n = s.length();

        int ans = 0;

        while (i < n && j < n) {
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
