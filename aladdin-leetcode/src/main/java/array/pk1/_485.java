package array.pk1;

public class _485 {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int tmp = 0;
        for (int num : nums) {
            if (num == 0) {
                tmp = 0;
            } else {
                tmp++;
                max = Math.max(max, tmp);
            }
        }
        return max;
    }
}
