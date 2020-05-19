import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class test {

    @Test
    public void test() {
        System.out.println(reverse(435));
    }

    public int reverse(int x) {
        boolean flag = true;
        if (x < 0) {
            flag = false;
        }

        String s = String.valueOf(x);
        char[] chars;
        if (flag) {
            chars = s.toCharArray();
        } else {
            chars = s.substring(1).toCharArray();
        }
        swap(chars, 0, chars.length - 1);

        String reversed = new String(chars);
        long l = Long.parseLong(reversed);
        if (flag) {
            if (l > Integer.MAX_VALUE) {
                return 0;
            }
            return (int) l;
        } else {
            if (-l < Integer.MIN_VALUE) {
                return 0;
            }
            return (int) -l;
        }
    }

    private void swap(char[] chars, int i, int j) {
        while (i < j) {
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
            i++;
            j--;
        }
    }

    @Test
    public void test2() {
        System.out.println(reverse2(Integer.MAX_VALUE));
    }

    public int reverse2(int i) {
        int sum = 0;
        while (i != 0) {
            int h = i % 10;
            int tmp = sum * 10 + h;
            long tmpL = (long)sum * 10 + h;
            if (tmp != tmpL){
                return 0;
            }
            sum = tmp;
            i = i / 10;
        }
        return sum;
    }



}

