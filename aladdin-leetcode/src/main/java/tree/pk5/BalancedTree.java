package tree.pk5;

import org.junit.Assert;
import org.junit.Test;
import tree.TreeNode;

public class BalancedTree {

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        if (Math.abs(left - right) <= 1) {
            return isBalanced(root.left) && isBalanced(root.right);
        }
        return false;
    }


    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }


    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        Assert.assertEquals(2, maxDepth(root));

        root.right = new TreeNode(3);
        Assert.assertEquals(2, maxDepth(root));

        root.right.left = new TreeNode(4);
        Assert.assertEquals(3, maxDepth(root));

        root.right.left.left = new TreeNode(5);
        Assert.assertEquals(4, maxDepth(root));
    }
}
