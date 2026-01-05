package leetcode.day08;

// LeetCode 2110: Number of Smooth Descent Periods of a Stock
// Difficulty: Medium
// Topic: DP, Array
// Time: O(n)
// Space: O(1)

public class NumberOfSmoothDescentPeriods_2110 {

    public long getDescentPeriods(int[] prices) {
        long ans = 1; // First element itself
        int dp = 1;   // Length of current descent sequence

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] == prices[i - 1] - 1) {
                dp++;
            } else {
                dp = 1;
            }
            ans += dp;
        }

        return ans;
    }
}
