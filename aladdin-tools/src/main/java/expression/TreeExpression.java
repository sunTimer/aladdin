package expression;

import java.util.Map;


public class TreeExpression {

    public TreeNode root;

    public TreeExpression(TreeNode root) {
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

        if (root.type == OperatorType.AND) {
            return execute(param, root.left) && execute(param, root.right);
        }

        if (root.type == OperatorType.OR) {
            return execute(param, root.left) || execute(param, root.right);
        }

        if (root.type == OperatorType.EQ) {
            TreeNode keyNode = root.left;
            TreeNode valueNode = root.right;
            return valueNode.value.equals(param.get(keyNode.value));
        }

        if (root.type == OperatorType.NEQ) {
            TreeNode keyNode = root.left;
            TreeNode valueNode = root.right;
            return !valueNode.value.equals(param.get(keyNode.value));
        }
        throw new IllegalArgumentException(root.type.symbol);
    }


    public Object execute0(Map<String, String> param, TreeNode root) {
        if (param == null) {
            return false;
        }

        TreeNode left;
        TreeNode right;
        String value;
        switch (root.type) {
            case AND:
                return (boolean) execute0(param, root.left) && (boolean) execute0(param, root.right);
            case OR:
                return (boolean) execute0(param, root.left) || (boolean) execute0(param, root.right);
            case EQ:
                left = root.left;
                right = root.right;
                if (left.type == OperatorType.KEY && right.type == OperatorType.KEY){

                }
                return param.get(left.value).equals(right.value);
            case NEQ:
                left = root.left;
                right = root.right;
                return !param.get(left.value).equals(right.value);
            case ADD:
                left = root.left;
                right = root.right;
                return Double.parseDouble(param.get(left.value)) + Double.parseDouble(param.get(right.value));
            case SUB:
                left = root.left;
                right = root.right;
                return Double.parseDouble(param.get(left.value)) - Double.parseDouble(param.get(right.value));
            case MULT:
                left = root.left;
                right = root.right;
                return Double.parseDouble(param.get(left.value)) * Double.parseDouble(param.get(right.value));
            case DIV:
                left = root.left;
                right = root.right;
                return Double.parseDouble(param.get(left.value)) / Double.parseDouble(param.get(right.value));
            default:
                throw new IllegalArgumentException(root.type.getSymbol());
        }
    }
}

