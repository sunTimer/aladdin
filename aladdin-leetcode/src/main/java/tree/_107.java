package tree;


import org.junit.Test;

import java.util.*;

public class _107 {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<List<Integer>> ret = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> subRet = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                subRet.add(treeNode.val);
                if (treeNode.left != null) {
                    queue.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.add(treeNode.right);
                }
            }
            ret.add(subRet);
        }
        Collections.reverse(ret);
        return ret;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.right = new TreeNode(4);

        List<List<Integer>> lists = levelOrderBottom(root);
        System.out.println(lists);
    }

}
