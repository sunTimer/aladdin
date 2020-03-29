package rule.milk;

/**
 * 只有叶子节点有值。其余节点只有类型。
 * 叶子节点的父亲节点一定是EQ或NEQ
 * EQ或NEQ的父亲节点一定是AND或OR（EQ或NEQ节点是根节点的情况排外）
 * AND或OR的父亲节点一定是AND或OR
 */
public class TreeNode {

    public TreeNode left;
    public TreeNode right;

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
                ", value='" + value + '\'' +
                ", operatorType=" + operatorType +
                '}';
    }
}
