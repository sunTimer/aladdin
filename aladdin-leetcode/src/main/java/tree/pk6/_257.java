package tree.pk6;

import org.junit.Test;
import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class _257 {

    @Test
    public void test(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
//        root.left.left = new TreeNode(4);
//        root.left.right = new TreeNode(5);
        List<String> strings = binaryTreePaths(root);
        System.out.println(strings);
    }
    List<String> ret = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        binaryTreePaths(root, new ArrayList<String>());
        return ret;
    }

    public void binaryTreePaths(TreeNode root, List<String> path) {
        if (root == null) {
            return;
        }

        path.add(root.val + "");

        if (root.left == null && root.right == null) {
            ret.add(String.join("->", path));
        }

        binaryTreePaths(root.left, path);
        binaryTreePaths(root.right, path);
        path.remove(path.size() - 1);
    }

}
