package tree.pk2;


import org.junit.Assert;
import org.junit.Test;
import tree.TreeNode;

public class _404 {
    public int sumOfLeftLeaves(TreeNode root) {
        return sum(root, 0);
    }

    public int sum(TreeNode root, int sum) {
        if (root == null) {
            return sum;
        }
        int leftSum = 0;
        if (root.left != null) {
            if (root.left.left == null && root.left.right == null) {
                leftSum = sum + root.left.val;
            } else {
                leftSum = sum(root.left, sum);
            }
        }
        return sum(root.right, sum) + leftSum;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(5);
        Assert.assertEquals(3, sumOfLeftLeaves(root));
    }
}
