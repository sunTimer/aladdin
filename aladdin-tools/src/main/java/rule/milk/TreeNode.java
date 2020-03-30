package rule.milk;

import java.util.*;

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
        return "tree{" +
                "left=" + left +
                ", right=" + right +
                ", v='" + value + '\'' +
                ", type=" + operatorType +
                '}';
    }

    public List<List<TreeNode>> levelOrder() {
        List<List<TreeNode>> ret = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(this);
        int size = 1;
        while (!queue.isEmpty()) {
            List<TreeNode> tmp = new ArrayList<>();
            int count = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                tmp.add(node);
                assert node != null;
                if (node.left != null) {
                    queue.add(node.left);
                    count++;
                }
                if (node.right != null) {
                    queue.add(node.right);
                    count++;

                }
            }
            size = count;
            ret.add(tmp);
        }
        return ret;
    }

    public void printTree() {
        List<List<TreeNode>> lists = levelOrder();
        int j = lists.size();
        for (List<TreeNode> list : lists) {
            for (TreeNode node : list) {
                for (int k = 0; k < j; k++) {
                    System.out.print("--");
                }
                if (node.operatorType == OperatorType.NUM) {
                    System.out.print(node.value);
                } else {
                    System.out.print(node.operatorType.getSymbol());
                }
            }
            j--;

            System.out.println();
        }
    }
}
