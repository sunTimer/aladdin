package stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 以 Unix 风格给出一个文件的绝对路径，你需要简化它。或者换句话说，将其转换为规范路径。
 * <p>
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。更多信息请参阅：Linux / Unix中的绝对路径 vs 相对路径
 * <p>
 * 请注意，返回的规范路径必须始终以斜杠 / 开头，并且两个目录名之间必须只有一个斜杠 /。最后一个目录名（如果存在）不能以 / 结尾。此外，规范路径必须是表示绝对路径的最短字符串。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入："/home/"
 * 输出："/home"
 * 解释：注意，最后一个目录名后面没有斜杠。
 * 示例 2：
 * <p>
 * 输入："/../"
 * 输出："/"
 * 解释：从根目录向上一级是不可行的，因为根是你可以到达的最高级。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/simplify-path
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _71 {

    public String simplifyPath2(String path) {
        Deque<String> stack = new LinkedList<>();
        for (String item : path.split("/")) {
            if (item.equals("..")) {
                if (!stack.isEmpty()) stack.pop();
            } else if (!item.isEmpty() && !item.equals(".")) stack.push(item);
        }
        String res = "";
        for (String d : stack) res = "/" + d + res;
        return res.isEmpty() ? "/" : res;
    }


    public String simplifyPath(String path) {


        char[] chars = path.toCharArray();
        Stack<Character> stack = new Stack<>();

        for (char aChar : chars) {
            if (stack.isEmpty()) {
                stack.push(aChar);
                continue;
            }

            char top = stack.peek();
            if (aChar == '/') {
                if (top == '.') {
                    stack.pop();
                } else if (top == aChar) {
                    // nothing to do.
                } else {
                    stack.push(aChar);
                }
            } else if (aChar == '.') {
                if (top == aChar) {
                    for (int i = 0; i < 3; i++) {
                        if (!stack.isEmpty() && stack.size() > 1 ) {
                            stack.pop();
                        }
                    }
                } else {
                    stack.push(aChar);
                }
            } else {
                stack.push(aChar);
            }
        }
        if (!stack.isEmpty()) {
            if (stack.size() > 1 && stack.peek() == '/') {
                stack.pop();
            }
            if (stack.peek() == '.') {
                stack.pop();
            }
        }
        StringBuilder stringBuilder = new StringBuilder();

        for (Character character : stack) {
            stringBuilder.append(character);
        }
        return stringBuilder.toString();
    }

    @Test
    public void test() {
        Assert.assertEquals("/a/b/c", simplifyPath("/a//b/c"));
        Assert.assertEquals("/a/b/c", simplifyPath("/a//b/./c"));
        Assert.assertEquals("/a/b/c", simplifyPath("/a//b////c/d//././/.."));

        Assert.assertEquals("/", simplifyPath("/."));
        Assert.assertEquals("/", simplifyPath("/.."));
        Assert.assertEquals("/", simplifyPath("/../"));
        Assert.assertEquals("/", simplifyPath("/./"));
    }
}
