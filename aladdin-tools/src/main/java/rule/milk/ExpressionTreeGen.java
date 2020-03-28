package rule.milk;

import java.util.Stack;

/**
 * <pre>
 *     m == 2 && n != 4 && h == 1
 *                    &&
 *                /       |
 *              ==       &&
 *            /   |    /    |
 *          m     2   !=    ==
 *                   /  |  / |
 *                  n   4  h  1
 * </pre>
 */
public class ExpressionTreeGen {

    public TreeNode buildTree(String[] words, int left, int right) {

        Stack<String> numStack = new Stack<>();
        Stack<String> operatorStack = new Stack<>();

        TreeNode root = null;
        TreeNode child;
        for (int i = left; i < right; i++) {
            String word = words[i];
            switch (word) {
                case "(":
                case "==":
                case "!=":
                    operatorStack.push(word);
                    break;
                case "&&":
                case "||":
                    TreeNode newRoot = new TreeNode(OperatorType.fromSymbol(word), null);
                    if (operatorStack.isEmpty()) {
                        newRoot.left = root;
                        root = newRoot;
                        continue;
                    } else {
                        child = getTreeNode(numStack, operatorStack);
                        if (root != null) {
                            if (root.left == null) {
                                root.left = child;
                                newRoot.left = root;
                            } else if (root.right == null) {
                                root.right = child;
                                newRoot.left = root;
                            }
                        } else {
                            newRoot.left = child;
                        }

                        root = newRoot;
                    }
                    if (numStack.isEmpty() && operatorStack.isEmpty()) {
                        root.right = buildTree(words, i + 1, right);
                    }
                    break;
                case ")":
                    while (!operatorStack.peek().equals("(")) {
                        child = getTreeNode(numStack, operatorStack);
                        if (root == null) {
                            root = child;
                        } else {
                            root.right = child;
                        }
                    }
                    operatorStack.pop();
                    break;
                case "#":
                    if (numStack.isEmpty() && operatorStack.isEmpty()) {
                        return root;
                    }
                    child = getTreeNode(numStack, operatorStack);
                    if (root == null) {
                        return child;
                    } else {
                        root.right = child;
                        return root;
                    }
                default:
                    numStack.push(word);
            }
        }
        return null;
    }

    private TreeNode getTreeNode(Stack<String> numStack, Stack<String> operStack) {
        String oper;
        TreeNode child;
        String value;
        String key;
        oper = operStack.pop();
        child = new TreeNode(OperatorType.fromSymbol(oper), null);
        value = numStack.pop();
        key = numStack.pop();
        child.left = new TreeNode(OperatorType.VALUE, key);
        child.right = new TreeNode(OperatorType.VALUE, value);
        return child;
    }
}
