package offer;

import org.junit.Test;

import java.io.DataInput;
import java.math.BigDecimal;
import java.util.Stack;

public class _31 {


    @Test
    public void test() {
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {4, 5, 3, 2, 1};
        System.out.println(validateStackSequences(a, b));
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {

        int start = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i : pushed) {
            stack.push(i);

            while (!stack.isEmpty() && stack.peek() == popped[start]){
                stack.pop();
                start++;
            }
        }
       return stack.isEmpty();
    }

    @Test
    public void test2(){
        BigDecimal decimal = new BigDecimal(2);
        BigDecimal decimal1 = new BigDecimal(9);
        BigDecimal divide = decimal.divide(decimal1, 2, BigDecimal.ROUND_UP);

        System.out.println(divide.doubleValue());
    }
}
