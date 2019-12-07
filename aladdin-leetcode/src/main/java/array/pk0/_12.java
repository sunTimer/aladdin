package array.pk0;

import org.junit.Test;

import java.util.List;

public class _12 {
    public String intToRoman(int num) {

        int[] numberArray = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanArray = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < numberArray.length; i++) {
            while (num >= numberArray[i]) {
                res.append(romanArray[i]);
                num -= numberArray[i];
            }
        }

        return res.toString();
    }

    public void help(int num, List<Integer> group) {
        if (num >= 1000) {
            handler(group, num, 1000);
        } else if (num >= 100) {
            handler(group, num, 100);
        } else if (num >= 10) {
            handler(group, num, 10);
        } else {
            group.add(num);
        }
    }

    public void handler(List<Integer> group, int num, int n) {
        int i = num / n;
        group.add(i * n);
        help(num % n, group);
    }

    @Test
    public void test() {
        System.out.println(intToRoman(1994));
    }
}
