package tree;


import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class TravelBinaryTree {

    // 二叉树的遍历
    // 1. 递归方式

    /**
     * 递归实现：先序遍历
     *
     * @param root 根节点
     */
    public void firstTravel(BinaryTreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val);
        firstTravel(root.left);
        firstTravel(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(5);

        System.out.println(levelOrder(root));
    }



    public static List<Integer> levelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        List<Integer> ret = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Queue<TreeNode> tmp = new LinkedList<>();
            boolean first = true;
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (first) {
                    ret.add(node.val);
                    first = false;
                }

                if (node.left != null) {
                    tmp.add(node.left);
                }

                if (node.right != null) {
                    tmp.add(node.right);
                }
            }

            if (!tmp.isEmpty()) {
                queue = tmp;
            }
        }

        return ret;
    }

    /**
     * 非递归实现：先序遍历
     *
     * @param root 根节点
     */
    public void firstTravelNoRecursion(BinaryTreeNode root) {
        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BinaryTreeNode curr = stack.pop();
            System.out.println(curr.val);
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
        }
    }

    /**
     * 递归：中序遍历
     *
     * @param root 根节点
     */
    public void middleTravel(BinaryTreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null) {
            System.out.println(root.val);
            middleTravel(root.right);
            return;
        }
        middleTravel(root.left);
        System.out.println(root.val);
        middleTravel(root.right);
    }

    /**
     * 非递归：中序遍历
     *
     * @param root 根节点
     */
    public void middleTravelNoRecursion(BinaryTreeNode root) {
        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
        stack.push(root);
        BinaryTreeNode p = root.left;
        while (p != null || !stack.isEmpty()) {
            // 一直访问左子树，直到左子树为空
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            // 左子树为空，此时栈顶元素为最后一个左子树，出栈并访问
            p = stack.pop();
            System.out.println(p.val);

            if (p.right != null) {
                p = p.right;
            } else {
                p = null;
            }
        }
    }

    /**
     * 递归：后续遍历
     *
     * @param root 根节点
     */
    public void postTravel(BinaryTreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null) {
            postTravel(root.right);
            System.out.println(root.val);
            return;
        }
        postTravel(root.left);
        postTravel(root.right);
        System.out.println(root.val);
    }

    /**
     * 非递归：后续遍历
     *
     * @param root 根节点
     */
    public void postTravelNoRecursion(BinaryTreeNode root) {
        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
        stack.push(root);
        BinaryTreeNode p = root.left;
        while (p != null || !stack.isEmpty()) {
            // 访问左子树
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            // 先拿到栈顶元素
            // 如果栈顶元素右子树为空，则访问，

            p = stack.peek();
            while (p.right == null && !stack.isEmpty()) {
                System.out.println(p.val);
                stack.pop();
            }
            p = p.right;

        }

    }

    private BinaryTreeNode root;

    @Before
    public void before() {
        root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.left.right = new BinaryTreeNode(6);
        root.right = new BinaryTreeNode(3);
        root.right.left = new BinaryTreeNode(4);
        root.left.right.left = new BinaryTreeNode(53);
    }

    @Test
    public void testFirstTravel() {
        firstTravel(root);
        System.out.println("------------");
        firstTravelNoRecursion(root);
    }

    @Test
    public void testMiddleTravel() {
        middleTravel(root);
        System.out.println("------------");
        middleTravelNoRecursion(root);
    }

    @Test
    public void testPostTravel() {
        postTravel(root);
        System.out.println("------------");
        postTravelNoRecursion(root);
    }
}
