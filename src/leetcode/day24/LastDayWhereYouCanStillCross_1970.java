package leetcode.day24;

import java.util.*;

// LeetCode 1970: Last Day Where You Can Still Cross
// Difficulty: Hard
// Topic: Binary Search, BFS, Grid Traversal
// Time: O((R * C) * log(R * C))
// Space: O(R * C)

public class LastDayWhereYouCanStillCross_1970 {

    private static final int[][] DIRS = {
            {-1, 0}, {0, 1}, {1, 0}, {0, -1}
    };

    public int latestDayToCross(int row, int col, int[][] cells) {
        int left = 0;
        int right = cells.length;

        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (canCross(row, col, cells, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean canCross(int row, int col, int[][] cells, int day) {

        boolean[][] water = new boolean[row][col];

        // Mark flooded cells
        for (int i = 0; i < day; i++) {
            int r = cells[i][0] - 1;
            int c = cells[i][1] - 1;
            water[r][c] = true;
        }

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[row][col];

        // Start BFS from top row
        for (int c = 0; c < col; c++) {
            if (!water[0][c]) {
                queue.offer(new int[]{0, c});
                visited[0][c] = true;
            }
        }

        // BFS
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];

            if (r == row - 1) {
                return true; // reached bottom row
            }

            for (int[] dir : DIRS) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < row &&
                        nc >= 0 && nc < col &&
                        !water[nr][nc] &&
                        !visited[nr][nc]) {

                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        return false; // no path found
    }
}
