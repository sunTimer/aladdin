package tree;

import org.junit.Test;

import java.util.LinkedList;

public class TreeTravel {

    /**
     * 1
     * / \
     * 2   3
     * \
     * 4
     * 1 -》 2 -》 4 -》 3
     *
     * @param root 根节点
     */
    public void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + "\t");
        preOrder(root.left);
        preOrder(root.right);
    }

    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        inOrder(root.left);
        System.out.print(root.val + "\t");
        inOrder(root.right);
    }


    public void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        inOrder(root.left);
        inOrder(root.right);
        System.out.print(root.val + "\t");
    }

    public void levelOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.pop();
            System.out.println(treeNode.val);
            if (treeNode.left != null) {
                queue.push(treeNode.left);
            }
            if (treeNode.right != null) {
                queue.push(treeNode.right);
            }
        }
    }

    //-----------------非递归实现---------------------
    public void inOrder_v2(TreeNode root) {

    }

    public void postOrder_v2(TreeNode root) {

    }

    /**
     * 1
     * / \
     * 2   3
     * \
     * 4
     * 1 -》 2 -》 4 -》 3
     *
     * @param root 根节点
     */
    public void preOrder_v2(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.val + "\t");
            if (node.left == null) {
                if (node.right != null) {
                    stack.push(node.right);
                }
            } else {
                stack.push(node.left);
                continue;
            }

            if (node.right != null) {
                stack.push(node.right);
            }
        }
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.left.right.left = new TreeNode(4);
        preOrder(root);
        System.out.println();
        preOrder_v2(root);
        System.out.println();
        inOrder(root);
        System.out.println();
        postOrder(root);
        System.out.println();

        levelOrder(root);
    }
}
