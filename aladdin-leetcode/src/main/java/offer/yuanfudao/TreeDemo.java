package offer.yuanfudao;

import org.junit.Test;
import tree.TreeNode;

public class TreeDemo {

    public TreeNode reverse(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode leftTail = root;

        reverse(root.left);
        reverse(root.right);

        leftTail.right = root.right;
        root.right = root.left;
        return leftTail;
    }

    public TreeNode preorder(TreeNode root) {
        if (root == null) {
            return null;
        }

        if (root.left != null){

        }

        if (root.right != null)
            return preorder(root.right);
        return root;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(preorder(root));
    }
}
