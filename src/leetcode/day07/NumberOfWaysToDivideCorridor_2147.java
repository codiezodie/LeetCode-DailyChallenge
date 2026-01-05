package leetcode.day07;

// LeetCode 2147: Number of Ways to Divide a Long Corridor
// Difficulty: Medium
// Topic: Greedy, Math
// Time: O(n)
// Space: O(1)

public class NumberOfWaysToDivideCorridor_2147 {

    public int numberOfWays(String corridor) {
        final int MOD = 1_000_000_007;

        long ans = 1;
        int prevSeatIndex = -1;
        int seatCount = 0;

        for (int i = 0; i < corridor.length(); i++) {
            if (corridor.charAt(i) == 'S') {
                seatCount++;

                // Every time we start a new pair (odd seat after first pair),
                // multiply the number of possible divider positions
                if (seatCount > 2 && seatCount % 2 == 1) {
                    ans = (ans * (i - prevSeatIndex)) % MOD;
                }

                prevSeatIndex = i;
            }
        }

        // Valid only if seats are even and at least 2
        return (seatCount > 1 && seatCount % 2 == 0) ? (int) ans : 0;
    }
}
