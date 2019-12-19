package tree;


import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class _101 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null){
            return true;
        }
        List<Integer> orderLeft = new ArrayList<>();
        List<Integer> orderRight = new ArrayList<>();
        orderLeft(root.left, orderLeft);
        orderRight(root.right, orderRight);

        if (orderLeft.size() != orderRight.size()) {
            return false;
        }
        for (int i = 0; i < orderLeft.size(); i++) {
            if (orderLeft.get(i) == null && orderRight.get(i) == null) {
                continue;
            }
            if (orderLeft.get(i) == null || orderRight.get(i) == null) {
                return false;
            }
            if (!orderLeft.get(i).equals(orderRight.get(i))) {
                return false;
            }
        }
        return true;
    }

    public void orderLeft(TreeNode root, List<Integer> orders) {
        if (root == null) {
            orders.add(null);
            return;
        }
        orders.add(root.val);
        orderLeft(root.left, orders);
        orderLeft(root.right, orders);
    }

    public void orderRight(TreeNode root, List<Integer> orders) {
        if (root == null) {
            orders.add(null);
            return;
        }
        orders.add(root.val);
        orderRight(root.right, orders);
        orderRight(root.left, orders);
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(8);
        root.left.right.right = new TreeNode(9);

        root.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.left.left = new TreeNode(9);
        root.right.left.right = new TreeNode(8);

        root.right.right = new TreeNode(4);
        Assert.assertTrue(isSymmetric(root));


        TreeNode root2 = new TreeNode(1);

        Assert.assertTrue(isSymmetric(root2));
    }
}
