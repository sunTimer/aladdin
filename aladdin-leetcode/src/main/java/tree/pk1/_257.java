package tree.pk1;

import org.junit.Test;
import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class _257 {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        StringBuilder curr = new StringBuilder();
        curr.append(root.val);
        path(curr, ret, root);
        return ret;
    }

    public void path(StringBuilder curr, List<String> ret, TreeNode root) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                ret.add(curr.toString());
                return;
            }
            if (root.left != null) {
                String tmp = curr.toString();
                curr.append("->").append(root.left.val);
                path(curr, ret, root.left);
                curr = new StringBuilder(tmp);
            }
            if (root.right != null) {
                curr.append("->").append(root.right.val);
                path(curr, ret, root.right);
            }
        }
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        System.out.println(binaryTreePaths(root));
    }
}
