package rule.milk;

import java.util.HashMap;
import java.util.Map;


public class ExpressionTree {

    public ExpressionTree(TreeNode root) {
        this.root = root;
    }

    public TreeNode root;

    public boolean execute(Map<String, String> param, TreeNode root) {
        if (root == null) {
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
            return param.get(keyNode.value).equals(valueNode.value);
        }

        if (root.operatorType == OperatorType.NEQ) {
            TreeNode keyNode = root.left;
            TreeNode valueNode = root.right;
            return !param.get(keyNode.value).equals(valueNode.value);
        }

        throw new IllegalArgumentException(root.operatorType.symbol);
    }
}

