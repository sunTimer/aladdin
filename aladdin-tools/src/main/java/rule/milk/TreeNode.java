package rule.milk;

public class TreeNode {

    public TreeNode left;
    public TreeNode right;

    public TreeNode parent;

    public String value;

    public OperatorType operatorType;

    public TreeNode(OperatorType operatorType, String value) {
        this.value = value;
        this.operatorType = operatorType;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "left=" + left +
                ", right=" + right +
                ", parent=" + parent +
                ", value='" + value + '\'' +
                ", operatorType=" + operatorType +
                '}';
    }
}
