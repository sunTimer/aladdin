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
public class BinTreeEvaluator {

    public BinaryTreeExpression compile(String[] words) {
        TreeNode root = treeifyBin(words, 0, words.length);
        return new BinaryTreeExpression(root);
    }

    /**
     * 根据中缀表达式构建树：
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

        if (left >= right) {
            return null;
        }

        Stack<String> numStack = new Stack<>();
        Stack<String> operatorStack = new Stack<>();

        TreeNode root = null;
        TreeNode child;
        while (left < right) {
            String word = words[left];
            switch (word) {
                case "(":
                case "==":
                case "!=":
                    // 低优先级操作符直接进操作符栈
                    operatorStack.push(word);
                    break;
                case "&&":
                case "||":
                    // && 或 || 为高优先级，要构建根节点
                    TreeNode newRoot = new TreeNode(OperatorType.fromSymbol(word), word);


                    if (numStack.isEmpty() && operatorStack.isEmpty()) {
                        // 如果此时栈是空的，说明左子树已经构建完毕。递归构建右子树
                        newRoot.left = root;
                        newRoot.right = treeifyBin(words, left + 1, right);
                        root = newRoot;
                        return root;
                    } else {
                        // 如果此时栈不为空，当前节点的左子树尚未构建。首先构建左子树
                        child = genOneTreeNode(numStack, operatorStack);

                        if (numStack.isEmpty() && operatorStack.isEmpty()) {
                            // child构建完以后，如果此时操作数栈和操作符栈均为空
                            newRoot.left = child;
                            newRoot.right = treeifyBin(words, left + 1, right);
                            root = newRoot;
                            return root;
                        } else {
                            if (root != null && root.right == null) {
                                root.right = child;
                                newRoot.left = root;
                            } else {
                                newRoot.left = child;
                            }
                            root = newRoot;
                        }
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
//                case "#":
//                    // # 代表结束符，此时需要判断操作符栈和操作数栈是否为空
//                    // 如果为空说明树已经构建完毕。如果不为空，则至多还存在一个节点没有生成。
//                    // 因此直接构建该节点即可，所以这里没有用循环遍历操作数栈和操作符栈
//                    if (numStack.isEmpty() && operatorStack.isEmpty()) {
//                        return root;
//                    }
//                    child = genOneTreeNode(numStack, operatorStack);
//                    if (root == null) {
//                        return child;
//                    } else {
//                        root.right = child;
//                        return root;
//                    }
                default:
                    // 若word不是以上任何类型，则说明word是一个操作数，直接进操作数栈
                    numStack.push(word);
            }
            left++;
        }

        if (left == right) {
            // # 代表结束，此时需要判断操作符栈和操作数栈是否为空
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
        }
        return root;
    }

    /**
     * 从操作符栈出栈一个操作符，作为根节点
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
        TreeNode root = new TreeNode(OperatorType.fromSymbol(oper), oper);

        // 进栈的顺序是先key后value，而出栈时是先value后key
        String value = numStack.pop();
        String key = numStack.pop();
        root.left = new TreeNode(OperatorType.NUM, key);
        root.right = new TreeNode(OperatorType.NUM, value);
        return root;
    }
}
