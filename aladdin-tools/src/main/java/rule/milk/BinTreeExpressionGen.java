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
public class BinTreeExpressionGen {

    public BinaryTreeExpression compile(String[] words) {
        TreeNode root = treeifyBin(words, 0, words.length);
        return new BinaryTreeExpression(root);
    }

    /**
     * 构建方法：
     * 1. 构建左子树
     * 2. 找到根节点
     * 3。 构建右子树
     *
     * @param words words
     * @param left  左侧索引
     * @param right 右侧索引
     * @return tree
     */
    public TreeNode treeifyBin(String[] words, int left, int right) {

        Stack<String> numStack = new Stack<>();
        Stack<String> operatorStack = new Stack<>();

        TreeNode root = null;
        TreeNode child;
        for (int i = left; i < right; i++) {
            String word = words[i];
            switch (word) {
                // 低优先级操作符直接进操作符栈
                case "(":
                case "==":
                case "!=":
                    operatorStack.push(word);
                    break;
                // && 或 ||为高优先级，要构建根节点
                case "&&":
                case "||":
                    TreeNode newRoot = new TreeNode(OperatorType.fromSymbol(word), null);
                    if (operatorStack.isEmpty()) {
                        newRoot.left = root;
                        root = newRoot;
                        continue;
                    } else {
                        child = genOneTreeNode(numStack, operatorStack);
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
                        // 如果此时操作数栈和操作符栈均为空，说明找到了树的根节点
                        // 并且左子树已经构建完毕，此时递归构建右子树即可
                        root.right = treeifyBin(words, i + 1, right);
                        return root;
                    }
                    break;
                case ")":
                    // 碰到右括号则不断的出栈构建子树，直到碰见左括号
                    // 如：（a == 1 && b != 2）
                    // 当遍历到右括号时，此时的操作数栈为：[b,2]。操作符栈为[(,!=]
                    // 因为&&符号的优先级高，因此&&及左侧的节点已经构建完毕
                    while (!operatorStack.peek().equals("(")) {
                        child = genOneTreeNode(numStack, operatorStack);
                        if (root == null) {
                            root = child;
                        } else {
                            root.right = child;
                        }
                    }
                    // 把左括号出栈
                    operatorStack.pop();
                    break;
                case "#":
                    // # 代表结束符，此时需要判断操作符栈和操作数栈是否为空
                    // 如果为空说明树已经构建完毕。如果不为空，则至多还存在一个节点没有生成。
                    // 因此直接构建该节点即可，所以这里没有用循环遍历操作数栈和操作符栈
                    if (numStack.isEmpty() && operatorStack.isEmpty()) {
                        return root;
                    }
                    child = genOneTreeNode(numStack, operatorStack);
                    if (root == null) {
                        return child;
                    } else {
                        root.right = child;
                        return root;
                    }
                default:
                    // 若word不是以上任何类型，则说明word是一个操作数，直接进操作数栈
                    numStack.push(word);
            }
        }
        return root;
    }

    /**
     * 从操作符栈出栈一个操作符，作为跟节点。
     * 从操作数栈连续出栈两个操作数，分别为右子树和左子树
     * 如：
     * <pre>
     *     操作符栈：[==, !=]
     *     操作数栈：[k1, v1, k2, v2]
     *     则最终返回的树：
     *           !=
     *         /   |
     *        k2  v2
     *     操作完以后，
     *     操作符栈：[==]
     *     操作数栈：[k1, v1]
     * </pre>
     *
     * @param numStack      操作数栈
     * @param operatorStack 操作符栈
     * @return treenode
     */
    private TreeNode genOneTreeNode(Stack<String> numStack, Stack<String> operatorStack) {
        String oper = operatorStack.pop();
        TreeNode root = new TreeNode(OperatorType.fromSymbol(oper), null);

        // 进栈的顺序是先key后value，而出栈时是先value后key
        String value = numStack.pop();
        String key = numStack.pop();
        root.left = new TreeNode(OperatorType.VALUE, key);
        root.right = new TreeNode(OperatorType.VALUE, value);
        return root;
    }
}
