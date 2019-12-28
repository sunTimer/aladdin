package array.pk1;

public class _268 {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int sum = 0;
        if (n % 2 == 0) {
            sum = (1 + n) * (n >> 1);
        } else {
            sum = n * ((n + 1) >> 1);
        }

        int realSum = 0;
        for (int num : nums) {
            realSum += num;
        }
        return sum - realSum;
    }
}
