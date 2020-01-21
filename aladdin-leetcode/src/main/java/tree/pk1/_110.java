package tree.pk1;

import org.junit.Assert;
import org.junit.Test;
import tree.TreeNode;

public class _110 {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (isB(root)) {
            return isBalanced(root.left) && isBalanced(root.right);
        }
        return false;
    }

    public boolean isB(TreeNode root) {
        if (Math.abs(dep(root.left, 0) - dep(root.right, 0)) > 1) {
            return false;
        }
        return true;
    }

    public int dep(TreeNode root, int depth) {
        if (root == null) {
            return depth;
        }
        depth++;
        return Math.max(dep(root.left, depth), dep(root.right, depth));
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        root.right = new TreeNode(0);
        Assert.assertTrue(isBalanced(root));
    }
}
