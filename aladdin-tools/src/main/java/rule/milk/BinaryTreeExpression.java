package rule.milk;

import java.util.Map;


public class BinaryTreeExpression {

    public TreeNode root;

    public BinaryTreeExpression(TreeNode root) {
        this.root = root;
    }

    public boolean execute(Map<String, String> param) {
        return execute(param, root);
    }

    /**
     * 后序遍历
     *
     * @param param 参数对
     * @param root  根节点
     * @return 执行结果
     */
    public boolean execute(Map<String, String> param, TreeNode root) {
        if (root == null || param == null || param.isEmpty()) {
            return false;
        }

        if (root.operatorType == OperatorType.AND) {
            return execute(param, root.left) && execute(param, root.right);
        }

        if (root.operatorType == OperatorType.OR) {
            return execute(param, root.left) || execute(param, root.right);
        }

        if (root.operatorType == OperatorType.EQ) {
            TreeNode keyNode = root.left;
            TreeNode valueNode = root.right;
            return valueNode.value.equals(param.get(keyNode.value));
        }

        if (root.operatorType == OperatorType.NEQ) {
            TreeNode keyNode = root.left;
            TreeNode valueNode = root.right;
            return !valueNode.value.equals(param.get(keyNode.value));
        }

        throw new IllegalArgumentException(root.operatorType.symbol);
    }
}

