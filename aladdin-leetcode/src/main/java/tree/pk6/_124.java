package tree.pk6;

import org.junit.Test;
import tree.TreeNode;

public class _124 {

    @Test
    public void test() {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        maxPathSum(root);
        System.out.println(ret);

    }

    int ret = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        max(root);
        return ret;
    }


    public int max(TreeNode root) {
        if (root == null) {
            return 0;
        }


        int left = root.left == null ? 0 : max(root.left);
        int right = root.right == null ? 0 : max(root.right);


        int sum = left + right + root.val;

        ret = Math.max(sum, ret);

        return Math.max(left, right) + root.val;
    }
}
