package tree.pk2;

import tree.TreeNode;

public class _563 {

    int sum = 0;

    public int findTilt(TreeNode root) {
        if (root == null) {
            return 0;
        }
        sum(root);
        return sum;
    }

    public int sum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = sum(root.left);
        int r = sum(root.right);
        sum += Math.abs(l - r);
        return l + r + root.val;
    }
}
