package rule.milk;

import java.util.Map;


public class BinTreeExpression {

    public TreeNode root;

    public BinTreeExpression(TreeNode root) {
        this.root = root;
    }

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

