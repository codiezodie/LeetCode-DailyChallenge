package leetcode.day29;

// LeetCode 1975: Maximum Matrix Sum
// Difficulty: Medium
// Topic: Greedy, Matrix
// Time: O(m * n)
// Space: O(1)

public class MaximumMatrixSum_1975 {

    public long maxMatrixSum(int[][] matrix) {
        long sum = 0;
        int negativeCount = 0;
        int minAbs = Integer.MAX_VALUE;

        for (int[] row : matrix) {
            for (int val : row) {
                if (val < 0) {
                    negativeCount++;
                }
                int abs = Math.abs(val);
                sum += abs;
                minAbs = Math.min(minAbs, abs);
            }
        }

        // If number of negatives is even, all can be made positive
        if (negativeCount % 2 == 0) {
            return sum;
        }

        // Otherwise, one smallest absolute value must remain negative
        return sum - 2L * minAbs;
    }
}
