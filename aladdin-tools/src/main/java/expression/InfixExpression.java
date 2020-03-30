package expression;

import rule.milk.TreeNode;

import java.util.Stack;

public class InfixExpression {
    public TreeNode treeifyBin(Token[] tokens) {

        Stack<Token> numStack = new Stack<>();
        Stack<Token> operatorStack = new Stack<>();

        Stack<TreeNode> stack = new Stack<>();

        for (Token token : tokens) {
            if (token.isNum()){
                numStack.push(token);
            }else {
                if (operatorStack.isEmpty()){
                    operatorStack.push(token);
                } else {
                    Token top = operatorStack.peek();
                    if (top.compareTo(token) >= 0) {

                    }
                }
            }
        }


        return null;
    }
}
