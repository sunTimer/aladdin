package tree.pk4;

import org.junit.Test;
import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class _94 {
    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> ret = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();

        while (root != null || !stack.isEmpty()) {

            if (root != null) {
                stack.push(root);
                root = root.left;
                continue;
            }

            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                ret.add(node.val);
                if (node.right != null) {
                    root = node.right;
                    break;
                }
            }
        }
        return ret;
    }


    @Test
    public void test() {
        /*
                        1
                      /  \
                     2    3
                    / \
                   4   5
                      / \
                     6   7
         */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(6);
        root.left.right.right = new TreeNode(7);
        System.out.println(inorderTraversal(root));
    }
}
