package tree.pk6;


import org.junit.Test;
import tree.TreeNode;

public class _114 {

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        flatten(root);
        System.out.println(root);
    }

    public void flatten(TreeNode root) {
        flattenHelp(root);
    }

    public TreeNode flattenHelp(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode flatLeft = flattenHelp(root.left);
        TreeNode flatRight = flattenHelp(root.right);
        if (flatLeft == null) {
            root.right = flatRight;
            return root;
        }

        root.right = flatLeft;
        root.left = null;

        while (flatLeft.right != null) {
            flatLeft = flatLeft.right;
        }
        flatLeft.right = flatRight;
        return root;
    }
}
