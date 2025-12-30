package leetcode.day02;
import java.util.*;

// Special Triplets
// Topic: HashMap, Prefix & Suffix Frequency
// Time: O(n)
// Space: O(n)
// LeetCode 3583: Count Special Triplets
// Difficulty: Medium
// Link: https://leetcode.com/problems/count-special-triplets/

public class CountSpecialTriplets_3583 {
    public int countSpecialTriplets(int[] nums) {
        final int MOD = 1_000_000_007;

        Map<Integer, Integer> left = new HashMap<>();
        Map<Integer, Integer> right = new HashMap<>();

        // Initialize right map with frequency of all elements
        for (int x : nums) {
            right.put(x, right.getOrDefault(x, 0) + 1);
        }

        long ans = 0;

        for (int x : nums) {
            // Remove current element from right
            right.put(x, right.get(x) - 1);

            int target = x * 2; // nums[i] = nums[k] = 2 * nums[j]

            long leftCount = left.getOrDefault(target, 0);
            long rightCount = right.getOrDefault(target, 0);

            ans = (ans + leftCount * rightCount) % MOD;

            // Add current element to left
            left.put(x, left.getOrDefault(x, 0) + 1);
        }

        return (int) ans;
    }
}
