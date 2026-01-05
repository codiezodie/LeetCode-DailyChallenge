package leetcode.day21;

import java.util.*;

// LeetCode 1351: Count Negative Numbers in a Sorted Matrix
// Difficulty: Easy
// Topic: Matrix
// Link: https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/

public class CountNegativeNumbersInSortedMatrix_1351 {

    public int countNegatives(int[][] grid) {
        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] < 0) {
                    count++;
                }
            }
        }
        return count;
    }
}
