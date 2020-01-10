package tree.pk2;

import org.junit.Test;
import tree.TreeNode;

public class _530 {

    Integer min = Integer.MAX_VALUE;
    TreeNode preNode = null;

    public int getMinimumDifference(TreeNode root) {
        getMin(root);
        return min;
    }

    private void getMin(TreeNode root) {
        if (root == null) {
            return;
        }
        getMin(root.left);
        if (preNode != null) {
            min = Math.min(min, Math.abs(root.val - preNode.val));
        }
        preNode = root;
        getMin(root.right);
    }


    @Test
    public void test() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);

        int minimumDifference = getMinimumDifference(root);
        System.out.println(minimumDifference);
    }
}
