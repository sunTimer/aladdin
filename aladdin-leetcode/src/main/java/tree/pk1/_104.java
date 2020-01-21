package tree.pk1;

import org.junit.Assert;
import org.junit.Test;
import tree.TreeNode;

public class _104 {

    public int maxDepth(TreeNode root) {
        return dep(root, 0);
    }

    public int dep(TreeNode root, int deps) {
        if (root == null) {
            return deps;
        }
        deps++;
        return Math.max(dep(root.left, deps), dep(root.right, deps));
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        int depth = maxDepth(root);
        Assert.assertEquals(3, depth);
    }
}
