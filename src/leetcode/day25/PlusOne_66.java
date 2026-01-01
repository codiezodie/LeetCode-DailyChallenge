package leetcode.day25;

// LeetCode 66: Plus One
// Difficulty: Easy
// Topic: Array
// Time: O(n)
// Space: O(n) (only in worst case)

public class PlusOne_66 {

    public int[] plusOne(int[] digits) {
        int n = digits.length;

        // Traverse from last digit
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }

        // If all digits were 9
        int[] newDigits = new int[n + 1];
        newDigits[0] = 1;
        return newDigits;
    }
}
