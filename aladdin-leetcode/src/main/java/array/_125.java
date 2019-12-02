package array;

import org.junit.Test;

public class _125 {

    public int maxProfit(int[] prices) {

        int maxValue = Integer.MIN_VALUE;
        int maxIndex = -1;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] > maxValue) {
                maxValue = prices[i];
                maxIndex = i;
            }
        }

        int maxLag = 0;
        for (int i = 0; i < maxIndex; i++) {
            int lag = maxValue - prices[i];
            maxLag = Math.max(maxLag, lag);
        }
        return maxLag;
    }

    @Test
    public void test(){


    }
}
