package leetcode.day32;

// LeetCode 1458: Max Dot Product of Two Subsequences
// Difficulty: Hard
// Topic: Dynamic Programming
// Time: O(m * n)
// Space: O(m * n)

public class MaxDotProductTwoSubsequences_1458 {

    public int maxDotProduct(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int[][] dp = new int[m + 1][n + 1];

        // Initialize with very small values
        for (int i = 0; i <= m; i++) {
            dp[i][0] = Integer.MIN_VALUE / 2;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = Integer.MIN_VALUE / 2;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                int skipNums1 = dp[i - 1][j];
                int skipNums2 = dp[i][j - 1];
                int skipBoth  = dp[i - 1][j - 1];

                int product = nums1[i - 1] * nums2[j - 1];
                int takeBoth = Math.max(product, dp[i - 1][j - 1] + product);

                dp[i][j] = Math.max(
                        Math.max(skipNums1, skipNums2),
                        Math.max(skipBoth, takeBoth)
                );
            }
        }

        return dp[m][n];
    }
}
