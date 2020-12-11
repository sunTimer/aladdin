package tree.pk6;

import org.junit.Test;
import tree.TreeNode;

public class BinSearchTree {

    /*
             18
           /   ｜
          3     39
               /  ｜
              25  88
     */
    TreeNode root = new TreeNode(18);

    {
        root.left = new TreeNode(3);
        root.right = new TreeNode(39);
        root.right.left = new TreeNode(25);
        root.right.right = new TreeNode(88);
    }

    @Test
    public void testSuccessor() {
        TreeNode successor = successor(root.right);
        System.out.println(successor.val);

        TreeNode presuccessor = presuccessor(root);
        System.out.println(presuccessor.val);
    }

    public TreeNode successor(TreeNode root) {
        if (root == null || root.right == null) {
            return null;
        }

        root = root.right;
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    public TreeNode presuccessor(TreeNode root){
        if (root == null || root.left == null) {
            return root;
        }

        root = root.left;

        while (root.right != null) {
            root = root.right;
        }
        return root;
    }
}
