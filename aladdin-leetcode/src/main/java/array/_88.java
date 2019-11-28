package array;

import org.junit.Test;

public class _88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] helper = new int[nums1.length];
        int i = 0;
        int j = 0;
        int f = 0;
        while (i < m || j < n) {
            if (i < m && j < n) {
                if (nums1[i] <= nums2[j]) {
                    helper[f++] = nums1[i++];
                } else {
                    helper[f++] = nums2[j++];
                }
            } else if (i < m) {
                helper[f++] = nums1[i++];
            } else {
                helper[f++] = nums2[j++];
            }
        }
        System.arraycopy(helper, 0, nums1, 0, helper.length);
    }

    @Test
    public void test() {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        merge(nums1, 3, nums2, 3);
        System.out.println("j");
    }
}
