package dp;

import org.junit.Assert;
import org.junit.Test;

public class Coin {

    @Test
    public void test() {
        Assert.assertEquals(2, minCoinNumber(6));
        Assert.assertEquals(2, minCoinNumber(7));
        Assert.assertEquals(3, minCoinNumber(8));
        Assert.assertEquals(3, minCoinNumber(9));
        Assert.assertEquals(2, minCoinNumber(10));
        Assert.assertEquals(3, minCoinNumber(11));
        Assert.assertEquals(10, minCoinNumber(50));
    }

    public int minCoinNumber(int amount) {
        int[] dp = new int[amount + 1];
        dp[1] = 1;
        dp[2] = 1;
        dp[5] = 1;

        for (int i = 1; i <= amount; i++) {
            if (i == 1
                    || i == 2
                    || i == 5) {
                dp[i] = 1;
                continue;
            }
            int tmp = Math.min(dp[i - 1], dp[i - 2]);
            if (i > 5) {
                dp[i] = Math.min(tmp, dp[i - 5]) + 1;
            } else {
                dp[i] = tmp + 1;
            }

        }
        return dp[amount];
    }
}
