package tree;

public class _226 {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        if (root.left != null && root.right != null) {
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;
            invertTree(root.left);
            invertTree(root.right);
        } else if (root.left != null) {
            root.right = root.left;
            invertTree(root.right);
            root.left = null;
        } else if (root.right != null) {
            root.left = root.right;
            invertTree(root.left);
            root.right = null;
        }
        return root;
    }
}
