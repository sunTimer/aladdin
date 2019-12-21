package tree;

public class _111 {
    public int minDepth(TreeNode root) {
        return minDep(root, 0);
    }

    private int minDep(TreeNode root, int min) {
        if (root == null) {
            return min;
        }
        if (root.left == null && root.right == null) {
            return min + 1;
        }
        min++;
        return Math.min(minDep(root.left, min), minDep(root.right, min));
    }


}
