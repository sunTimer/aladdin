package tree;

public class _112 {

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        int lag = sum - root.val;
        if (lag == 0) {
            return true;
        }

        return hasPathSum(root.left, lag) || hasPathSum(root.right, lag);
    }
}
