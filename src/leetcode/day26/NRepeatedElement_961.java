package leetcode.day26;

import java.util.HashSet;

// LeetCode 961: N-Repeated Element in Size 2N Array
// Difficulty: Easy
// Topic: HashSet
// Time: O(n)
// Space: O(n)

public class NRepeatedElement_961 {

    public int repeatedNTimes(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            if (set.contains(num)) {
                return num;
            }
            set.add(num);
        }

        // As per problem statement, this line is never reached
        return -1;
    }
}
