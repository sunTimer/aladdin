package stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 判断字符串是否时合法的括号 {@easy}
 */
public class _20 {

    private Map<Character, Character> markMaps = new HashMap<>(3);
    {
        markMaps.put('{', '}');
        markMaps.put('(', ')');
        markMaps.put('[', ']');
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (markMaps.containsKey(c)) {
                stack.push(c);
            } else {
                if (markMaps.values().contains(c)) {
                    if (stack.isEmpty()) {
                        return false;
                    }
                    if (c == markMaps.get(stack.peek())) {
                        stack.pop();
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    @Test
    public void test() {
        Assert.assertTrue(isValid("{}"));
        Assert.assertTrue(isValid("{[]}"));
        Assert.assertTrue(isValid("{}{}()[{()}]"));
        Assert.assertFalse(isValid("{"));
        Assert.assertFalse(isValid("]"));
    }
}
