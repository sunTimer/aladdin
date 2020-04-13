package expression;

import org.junit.Test;

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
    public OperatorType type;

    public TreeNode(OperatorType type, String value) {
        this.value = value;
        this.type = type;
    }

    @Override
    public String toString() {
        return "tree{" +
                "left=" + left +
                ", right=" + right +
                ", v='" + value + '\'' +
                ", type=" + type +
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
                if (node.type == OperatorType.NUM) {
                    System.out.print(node.value);
                } else {
                    System.out.print(node.type.getSymbol());
                }
            }
            j--;

            System.out.println();
        }
    }

    public static boolean equals(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }

        if (p.value.equals(q.value)) {
            return equals(p.left, q.left) && equals(p.right, q.right)
                    || equals(p.left, q.right) && equals(p.right, q.left);
        }
        return false;
    }

    public static void main(String[] args) {

        TreeEvaluator treeEvaluator = new TreeEvaluator();
        TreeExpression p = treeEvaluator.compile("c == 3 && a == 1 && b == 2");
        TreeExpression q = treeEvaluator.compile("b == 2 && a == 1 && c == 3");

        p.root.printTree();
        q.root.printTree();

        System.out.println(equals(p.root, q.root));
    }


}
