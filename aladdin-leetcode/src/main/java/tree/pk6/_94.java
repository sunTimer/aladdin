package tree.pk6;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class _94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();

        if (root == null) {
            return ret;
        }

        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();

        while (root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
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
}
