package leetcode.day03;

// Count Permutations (Unlock Computers)
// Topic: Math, Permutations
// Time: O(n)
// Space: O(1)

public class CountPermutations_UnlockComputers_3577{

    private static final long MOD = 1_000_000_007L;

    public int countPermutations(int[] complexity) {
        int n = complexity.length;

        // Computer 0 must be able to unlock every other computer
        // So complexity[0] must be strictly smaller than all others
        for (int i = 1; i < n; i++) {
            if (complexity[0] >= complexity[i]) {
                return 0;
            }
        }

        // If valid, result = (n - 1)!
        long ans = 1;
        for (int i = 2; i <= n - 1; i++) {
            ans = (ans * i) % MOD;
        }

        return (int) ans;
    }
}
