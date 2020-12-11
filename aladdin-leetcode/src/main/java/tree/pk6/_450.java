package tree.pk6;

import tree.TreeNode;

public class _450 {




    public TreeNode deleteNode(TreeNode root, int key) {

        if (root == null) {
            return null;
        }

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
            return root;
        }

        if (key > root.val) {
            root.right = deleteNode(root.right, key);
            return root;
        }

        if (root.left == null) {
            return root.right;
        }

        if (root.right == null) {
            return root.left;
        }


        // 1. 获取到根节点的后继节点
        TreeNode successor = successor(root);
        if (successor == null) {
            return root.left;
        }
        successor.left = root.left;
        return root.right;
    }

    private TreeNode successor(TreeNode root) {
        if (root == null || root.right == null) {
            return null;
        }
        root = root.right;
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }
}
