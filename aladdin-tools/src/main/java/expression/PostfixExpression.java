package expression;

import rule.milk.OperatorType;
import rule.milk.TreeNode;

import java.util.Stack;

/**
 * 后缀表达式求值
 */
public class PostfixExpression {

    public Token[] infixToPostfix(Token[] tokens) {
        // 4.99 * 1.06 + 5.99 + 6.99 * 1.06
        // 4.99 1.06 * 5.99 + 6.99 1.06 * +
        Stack<Token> numStack = new Stack<>();
        Stack<Token> operatorStack = new Stack<>();
        for (Token currToken : tokens) {
            if (currToken.isNum()) {
                // 数字直接进操作数栈
                numStack.push(currToken);
            } else if (currToken.isLeftMark()) {
                // 左括号直接进操作符栈
                operatorStack.push(currToken);
            } else if (currToken.isRightMark()) {
                // 如果碰到右括号，则循环出操作符栈，直到遇见左括号
                while (!operatorStack.isEmpty()) {
                    Token top = operatorStack.pop();
                    if (!top.isLeftMark()) {
                        numStack.push(top);
                    } else {
                        break;
                    }
                }
            } else {
                while (true) {
                    if (operatorStack.isEmpty()) {
                        operatorStack.push(currToken);
                        break;
                    }
                    Token topToken = operatorStack.peek();
                    if (topToken.compareTo(currToken) >= 0) {
                        operatorStack.pop();
                        numStack.push(topToken);
                    } else {
                        operatorStack.push(currToken);
                        break;
                    }
                }
            }
        }
        while (!operatorStack.isEmpty()) {
            numStack.push(operatorStack.pop());
        }

        Token[] ret = new Token[numStack.size()];
        for (int i = ret.length - 1; i >= 0; i--) {
            ret[i] = numStack.pop();
        }
        return ret;
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
        Stack<TreeNode> stack = new Stack<>();

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

