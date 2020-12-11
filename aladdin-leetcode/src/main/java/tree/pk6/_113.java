package tree.pk6;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class _113 {
    List<List<Integer>> ret = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        helper(root, sum, new ArrayList<Integer>());
        return ret;
    }

    public void helper(TreeNode root, int sum, List<Integer> list) {
        if (root == null) {
            return;
        }

        list.add(root.val);
        sum = sum - root.val;
        if (root.left == null && root.right == null && sum == 0) {
            ret.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
            return;
        }

        helper(root.left, sum, list);
        helper(root.right, sum, list);
        list.remove(list.size() - 1);
    }

}
