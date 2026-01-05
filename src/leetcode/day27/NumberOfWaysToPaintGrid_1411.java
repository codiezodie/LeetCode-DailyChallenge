package leetcode.day27;

// LeetCode 1411: Number of Ways to Paint N × 3 Grid
// Difficulty: Hard
// Topic: DP
// Time: O(n)
// Space: O(1)

public class NumberOfWaysToPaintGrid_1411 {

    public int numOfWays(int n) {
        long MOD = 1_000_000_007;

        // a = number of ways with pattern ABA
        // b = number of ways with pattern ABC
        long a = 6;
        long b = 6;

        for (int i = 2; i <= n; i++) {
            long newA = (a * 3 + b * 2) % MOD;
            long newB = (a * 2 + b * 2) % MOD;
            a = newA;
            b = newB;
        }

        return (int) ((a + b) % MOD);
    }
}
