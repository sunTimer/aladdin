package tree;

import org.junit.Test;

public class _112 {

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        int lag = sum - root.val;
        if (root.left == null && root.right == null){
            return lag == 0;
        }
        return hasPathSum(root.left, lag) || hasPathSum(root.right, lag);
    }

    @Test
    public void test() {
    }
}
