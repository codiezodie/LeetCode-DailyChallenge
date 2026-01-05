package leetcode.day10;

import java.util.Arrays;

// LeetCode 3573: Maximum Profit
// Topic: Dynamic Programming, State Machine
// Difficulty: Hard
// Time: O(n * k)
// Space: O(k)

public class MaximumProfit_3573 {

    public long maximumProfit(int[] prices, int k) {
        int n = prices.length;
        long NEG = Long.MIN_VALUE / 2;

        // dp[t][state]
        // state 0: no position
        // state 1: holding a stock (buy)
        // state 2: holding a short position (short sell)
        long[][] dp = new long[k + 1][3];

        for (int i = 0; i <= k; i++) {
            Arrays.fill(dp[i], NEG);
            dp[i][0] = 0; // start with no position
        }

        for (int price : prices) {
            long[][] next = new long[k + 1][3];
            for (int i = 0; i <= k; i++) {
                Arrays.fill(next[i], NEG);
            }

            for (int t = 0; t <= k; t++) {

                // stay idle
                next[t][0] = Math.max(next[t][0], dp[t][0]);
                next[t][1] = Math.max(next[t][1], dp[t][1]);
                next[t][2] = Math.max(next[t][2], dp[t][2]);

                // open positions
                next[t][1] = Math.max(next[t][1], dp[t][0] - price); // buy
                next[t][2] = Math.max(next[t][2], dp[t][0] + price); // short sell

                // close positions
                if (t + 1 <= k) {
                    next[t + 1][0] = Math.max(next[t + 1][0], dp[t][1] + price); // sell
                    next[t + 1][0] = Math.max(next[t + 1][0], dp[t][2] - price); // buy back
                }
            }
            dp = next;
        }

        long ans = 0;
        for (int t = 0; t <= k; t++) {
            ans = Math.max(ans, dp[t][0]);
        }
        return ans;
    }
}
