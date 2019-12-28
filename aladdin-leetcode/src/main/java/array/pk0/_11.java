package array.pk0;

import org.junit.Test;

public class _11 {

    public int maxArea(int[] height) {

        int max = 0;

        for (int i = 0; i < height.length - 1; i++) {
            for (int j = 1; j < height.length; j++) {
                int minHeight = Math.min(height[i], height[j]);
                max = Math.max(max, (j - i) * minHeight);
            }
        }
        return max;
    }

    public int maxArea2(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while (left < right) {
            max = Math.max(max, (right - left) * Math.min(height[left], height[right]));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }

    @Test
    public void test() {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(height));
        System.out.println(maxArea2(height));
    }
}
