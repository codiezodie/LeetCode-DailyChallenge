package leetcode.day11;

// LeetCode 3652: Max Profit With Strategy
// Difficulty: Hard
// Topic: Sliding Window, Greedy
// Time: O(n)
// Space: O(n)

public class MaxProfitWithStrategy_3652 {

    public long maxProfit(int[] prices, int[] strategy, int k) {

        int n = prices.length;
        long baseProfit = 0;

        // Base profit using original strategy
        for (int i = 0; i < n; i++) {
            baseProfit += (long) strategy[i] * prices[i];
        }

        int half = k / 2;
        long[] holdGain = new long[n];
        long[] sellGain = new long[n];

        // Precompute gains
        for (int i = 0; i < n; i++) {
            holdGain[i] = (0L - strategy[i]) * prices[i];
            sellGain[i] = (1L - strategy[i]) * prices[i];
        }

        long currGain = 0;

        // Initial window
        for (int i = 0; i < half; i++) {
            currGain += holdGain[i];
        }
        for (int i = half; i < k; i++) {
            currGain += sellGain[i];
        }

        long maxGain = currGain;

        // Slide window
        for (int l = 1; l + k - 1 < n; l++) {
            int r = l + k - 1;

            currGain -= holdGain[l - 1];
            currGain += holdGain[l + half - 1];

            currGain -= sellGain[l + half - 1];
            currGain += sellGain[r];

            maxGain = Math.max(maxGain, currGain);
        }

        return baseProfit + Math.max(0, maxGain);
    }
}
