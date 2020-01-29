package offer;

import org.junit.Test;
import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FindPath {

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.left.right = new TreeNode(9);
        ArrayList<ArrayList<Integer>> path = findPath(root, 17);

        System.out.println(path);
    }


    private List<List<Integer>> ret = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> findPath(TreeNode root, int target) {

        if (root == null) {
            return new ArrayList<>();
        }

        find(root, target, new LinkedList<>());
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (List<Integer> l : ret) {
            ArrayList<Integer> m = new ArrayList<>(l.size());
            m.addAll(l);
            result.add(m);
        }

        return result;
    }

    private void find(TreeNode root, int target, List<Integer> pathList) {
        if (root != null) {
            pathList.add(root.val);
            if (root.left == null && root.right == null) {
                if (sum(pathList) == target) {
                    ret.add(pathList);
                }
            } else {
                List<Integer> left = new LinkedList<>(pathList);
                find(root.left, target, left);

                List<Integer> right = new LinkedList<>(pathList);
                find(root.right, target, right);
            }
        }
    }

    public int sum(List<Integer> nums) {
        int sum = 0;
        for (Integer num : nums) {
            sum += num;
        }
        return sum;
    }
}
