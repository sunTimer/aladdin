package offer.yuanfudao;

import org.junit.Test;

import java.util.Stack;

/**
 * 对一个栈进行排序
 */
public class StackSort {

    @Test
    public void test(){
        Stack<Integer> nums = new Stack<>();
        nums.push(3);
        nums.push(12);
        nums.push(1);
        nums.push(32);
        nums.push(124);
        nums.push(-1);
        System.out.println(sort(nums));
    }

    public Stack<Integer> sort(Stack<Integer> target) {
        Stack<Integer> helper = new Stack<>();

        helper.push(target.pop());

        while (!target.isEmpty()) {
            int top = target.pop();
            int count = 0;
            while (!helper.isEmpty() && helper.peek() < top){
                count++;
                target.push(helper.pop());
            }

            helper.push(top);

            for (int i = 0; i < count; i++) {
                helper.push(target.pop());
            }
        }
        return helper;
    }
}
