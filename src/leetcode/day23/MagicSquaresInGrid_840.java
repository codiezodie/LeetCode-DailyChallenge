package leetcode.day23;

import java.util.*;

// LeetCode 840: Magic Squares In Grid
// Difficulty: Medium
// Topic: Matrix, Simulation
// Link: https://leetcode.com/problems/magic-squares-in-grid/

public class MagicSquaresInGrid_840 {

    public int numMagicSquaresInside(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        for (int i = 0; i <= rows - 3; i++) {
            for (int j = 0; j <= cols - 3; j++) {
                if (isMagic(grid, i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isMagic(int[][] grid, int r, int c) {
        int[] freq = new int[16];
        int d1 = 0, d2 = 0;

        int target = grid[r][c] + grid[r][c + 1] + grid[r][c + 2];

        for (int i = 0; i < 3; i++) {
            int rowSum = 0, colSum = 0;

            for (int j = 0; j < 3; j++) {
                int val = grid[r + i][c + j];

                // Values must be 1 to 9 and unique
                if (val < 1 || val > 9 || ++freq[val] > 1) {
                    return false;
                }

                rowSum += val;
                colSum += grid[r + j][c + i];

                if (i == j) d1 += val;
                if (i + j == 2) d2 += val;
            }

            if (rowSum != target || colSum != target) {
                return false;
            }
        }

        return d1 == target && d2 == target;
    }
}
