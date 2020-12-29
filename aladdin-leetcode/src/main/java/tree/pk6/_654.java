package tree.pk6;

import org.junit.Test;
import tree.TreeNode;

public class _654 {

    @Test
    public void test() {
        int[] nums = {3, 2, 1, 6, 0, 5};
        TreeNode node = constructMaximumBinaryTree(nums);
        System.out.println(node);
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree(nums, 0, nums.length - 1);
    }

    public TreeNode constructMaximumBinaryTree(int[] nums, int left, int right) {
        if (left <= right) {
            int max = findMax(left, right, nums);
            TreeNode root = new TreeNode(nums[max]);
            root.left = constructMaximumBinaryTree(nums, left, max - 1);
            root.right = constructMaximumBinaryTree(nums, max + 1, right);
            return root;
        }
        return null;
    }

    public int findMax(int left, int right, int[] nums) {
        int max = left;
        while (left <= right) {
            if (nums[left] > nums[max]) {
                max = left;
            }
            left++;
        }
        return max;
    }

}
