package array.pk0;

import org.junit.Assert;
import org.junit.Test;

public class _125 {

    public int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                max = Math.max(profit, max);
            }
        }
        return max;
    }


    public int maxProfit2(int[] prices) {
        int max = 0;
        int buyDay = 0;

        for (int i = 1; i < prices.length; i++) {
            int todayPrice = prices[i];
            int buyDayPrice = prices[buyDay];
            if (todayPrice > buyDayPrice) {
                max = Math.max(max, todayPrice - buyDayPrice);
            } else if (todayPrice < buyDayPrice) {
                buyDay = i;
            }
        }
        return max;
    }

    @Test
    public void test() {
        int[] nums = {7, 1, 5, 3, 6, 4};
        Assert.assertEquals(5, maxProfit2(nums));

        int[] nums1 = {7, 1, 5, 3, 6, 4, 1, 3, 2, 102};
        Assert.assertEquals(101, maxProfit2(nums1));


        int[] nums2 = {7, 5, 4, 3, 2};
        Assert.assertEquals(0, maxProfit2(nums2));

        int[] nums3 = {6, 7};
        Assert.assertEquals(1, maxProfit2(nums3));
    }
}
