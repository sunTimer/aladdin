package expression;

import java.util.*;

/**
 * 后缀表达式求值
 */
public class PostfixExpression {

    public List<Token> infixToPostfix(List<Token> tokens) {
        // 4.99 * 1.06 + 5.99 + 6.99 * 1.06
        // 4.99 1.06 * 5.99 + 6.99 1.06 * +
        LinkedList<Token> postfixStack = new LinkedList<>();
        LinkedList<Token> tmpStack = new LinkedList<>();
        for (Token currToken : tokens) {
            if (currToken.isNum()) {
                // 数字直接进操作数栈
                postfixStack.push(currToken);
            } else if (currToken.isLeftMark()) {
                // 左括号直接进操作符栈
                tmpStack.push(currToken);
            } else if (currToken.isRightMark()) {
                // 如果碰到右括号，则循环出操作符栈，直到遇见左括号
                while (!tmpStack.isEmpty()) {
                    Token top = tmpStack.pop();
                    if (!top.isLeftMark()) {
                        postfixStack.push(top);
                    } else {
                        break;
                    }
                }
            } else {
                while (true) {
                    if (tmpStack.isEmpty()) {
                        tmpStack.push(currToken);
                        break;
                    }
                    Token topToken = tmpStack.peek();
                    if (topToken.compareTo(currToken) >= 0) {
                        tmpStack.pop();
                        postfixStack.push(topToken);
                    } else {
                        tmpStack.push(currToken);
                        break;
                    }
                }
            }
        }
        while (!tmpStack.isEmpty()) {
            postfixStack.push(tmpStack.pop());
        }

        Collections.reverse(postfixStack);
        return postfixStack;
    }

    public double execute(Token[] tokens) {
        Stack<Double> stack = new Stack<>();
        for (Token token : tokens) {
            String value = token.getValue();
            if (token.isNum()) {
                stack.push(Double.valueOf(value));
            } else {
                double a = stack.pop();
                double b = stack.pop();
                switch (value) {
                    case "+":
                        stack.push(a + b);
                        break;
                    case "-":
                        stack.push(b - a);
                        break;
                    case "*":
                        stack.push(a * b);
                        break;
                    case "/":
                        stack.push(b / a);
                        break;
                }
            }
        }
        return stack.pop();
    }

    // a == 1 && b == 2  -> a 1 ==  b 2 == &
    // (a + 1) * (b + 2) ->  a 1 + b 2 + *

    public TreeNode treeifyBin(Token[] tokens) {
        LinkedList<TreeNode> stack = new LinkedList<>();

        for (Token token : tokens) {
            String value = token.getValue();
            if (token.isNum()) {
                TreeNode treeNode = new TreeNode(OperatorType.NUM, value);
                stack.push(treeNode);
            } else {
                TreeNode right = stack.pop();
                TreeNode left = stack.pop();

                TreeNode root = new TreeNode(OperatorType.fromSymbol(value), value);
                root.left = left;
                root.right = right;
                stack.push(root);
            }
        }
        return stack.pop();
    }

    public TreeNode treeifyBin(List<Token> tokens) {
        LinkedList<TreeNode> stack = new LinkedList<>();

        for (Token token : tokens) {
            String value = token.getValue();
            if (token.isNum()) {
                TreeNode treeNode = new TreeNode(OperatorType.NUM, value);
                stack.push(treeNode);
            } else {
                TreeNode right = stack.pop();
                TreeNode left = stack.pop();

                TreeNode root = new TreeNode(OperatorType.fromSymbol(value), value);
                root.left = left;
                root.right = right;
                stack.push(root);
            }
        }
        return stack.pop();
    }
}

