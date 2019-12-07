package array.pk0;

import org.junit.Assert;
import org.junit.Test;

public class _122 {

    public int maxProfit(int[] prices) {

        int buyDay = 0;

        int sum = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[buyDay]) {
                sum += prices[i] - prices[buyDay];
            }
            buyDay = i;
        }
        return sum;
    }

    @Test
    public void test() {
        int[] prices = {7, 6, 4, 3, 1};
        Assert.assertEquals(0, maxProfit(prices));

        int[] prices2 = {1, 2, 3, 4, 5};
        Assert.assertEquals(4, maxProfit(prices2));

        int[] prices3 = {7, 1, 5, 3, 6, 4};
        Assert.assertEquals(7, maxProfit(prices3));
    }
}
