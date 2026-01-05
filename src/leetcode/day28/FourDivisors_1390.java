package leetcode.day28;

// LeetCode 1390: Four Divisors
// Difficulty: Medium
// Topic: Math, Number Theory
// Time: O(n * sqrt(max(nums)))
// Space: O(1)

public class FourDivisors_1390 {

    public int sumFourDivisors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int ans = 0;

        for (int num : nums) {
            int firstDivisor = 0;

            // Find divisors excluding 1 and num
            for (int i = 2; i * i <= num; i++) {
                if (num % i == 0) {
                    if (firstDivisor == 0) {
                        firstDivisor = i;        // first non-trivial divisor
                    } else {
                        firstDivisor = 0;        // more than one pair → invalid
                        break;
                    }
                }
            }

            // Exactly one divisor pair found
            if (firstDivisor > 0 && firstDivisor * firstDivisor < num) {
                ans += 1 + num + firstDivisor + num / firstDivisor;
            }
        }

        return ans;
    }
}
