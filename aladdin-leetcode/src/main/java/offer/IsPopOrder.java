package offer;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

public class IsPopOrder {

    public boolean IsPopOrder(int[] pushA, int[] popA) {
        Stack<Integer> stack = new Stack<>();
        int popIndex = 0;


        for (int i : pushA) {
            if (i == popA[popIndex]) {
                popIndex++;
                continue;
            }

            stack.push(i);
        }
        for (int i = popIndex; i < popA.length; i++) {
            if (stack.peek() == popA[i]) {
                stack.pop();
            }
        }

        return stack.isEmpty();
    }

    @Test
    public void test() {
        int[] push = {1, 2, 3, 4, 5};
        int[] pop = {1, 2, 3, 4, 5};
        Assert.assertTrue(IsPopOrder(push, pop));
    }
}
