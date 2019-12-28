package stack;

import org.junit.Assert;

import java.util.Stack;

public class _155_MinStack {
    /** initialize your data structure here. */

    Stack<Integer> data;
    Stack<Integer> minData;

    public _155_MinStack() {
        data = new Stack<>();
        minData =new Stack<>();
    }

    public void push(int x) {
        data.push(x);
        if(minData.isEmpty()){
            minData.push(x);
        }else if (minData.peek() > x){
            minData.push(x);
        }
    }

    public void pop() {
        data.pop();
        if (minData.size() == data.size()){
            minData.pop();
        }
    }

    public int top() {
        return data.peek();
    }

    public int getMin() {
        return minData.peek();
    }

    public static void main(String[] args) {
        _155_MinStack minStack = new _155_MinStack();
        minStack.push(2);
        Assert.assertEquals(2, minStack.getMin());

        minStack.push(5);
        Assert.assertEquals(2, minStack.getMin());


        minStack.push(43);
        minStack.push(-1);
        Assert.assertEquals(-1, minStack.getMin());


    }
}
