package tree.pk5;

import org.junit.Test;
import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Order {

    List<Integer> order = new ArrayList<>();

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        System.out.println(postorder(root));
    }

    public List<Integer> postorder(TreeNode root) {
        if (root == null) {
            return order;
        }

        postorder(root.left);
        postorder(root.right);
        order.add(root.val);
        return order;
    }

}
