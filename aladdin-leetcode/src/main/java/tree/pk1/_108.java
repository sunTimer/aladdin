package tree.pk1;

import tree.TreeNode;

public class _108 {

    public TreeNode sortedArrayToBST(int[] nums) {
        return nums == null ? null : buildTree(nums, 0, nums.length - 1);
    }

    public TreeNode buildTree(int[] nums, int i, int j) {
        if (i > j) {
            return null;
        }
        int mid = (i + j) >> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildTree(nums, i, mid - 1);
        root.right = buildTree(nums, mid + 1, j);
        return root;
    }
}
