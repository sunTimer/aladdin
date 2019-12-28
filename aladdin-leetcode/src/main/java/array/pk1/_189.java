package array.pk1;

import org.junit.Test;

public class _189 {

    public void rotate(int[] nums, int k) {
        if ((k = k % nums.length) == 0) {
            return;
        }

        int count = 0;
        for (int i = 0; count < nums.length; i++) {
            int current = i;
            int prev = nums[current];
            do {
                int next = (current + k) % nums.length;
                current = next;
                int tmp = nums[next];
                nums[next] = prev;
                prev = tmp;
                count++;
            } while (current != i);
        }
    }

    @Test
    public void test() {
        int[] nums = {1, 2, 3, 4, 5, 6};
        rotate(nums, 2);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
