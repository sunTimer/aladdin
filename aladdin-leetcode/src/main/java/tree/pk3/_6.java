package tree.pk3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * <p>
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _6 {

    @Test
    public void test() {
        String s = "leetcodeishiring";
        String convert = convert(s, 3);
        System.out.println(convert);
    }

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        List<StringBuilder> cache = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            cache.add(new StringBuilder());
        }

        char[] chars = s.toCharArray();
        int flag = -1;
        int index = 0;
        for (char aChar : chars) {
            if (index == 0 || (index + 1 == numRows)) {
                flag = -flag;
            }
            cache.get(index).append(aChar);
            index += flag;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder str : cache) {
            ret.append(str);
        }
        return ret.toString();
    }
}
