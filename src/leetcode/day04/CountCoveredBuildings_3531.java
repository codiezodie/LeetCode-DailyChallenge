package leetcode.day04;

import java.util.*;

// LeetCode 3531: Count Covered Buildings
// Difficulty: Medium
// Topic: HashMap, Sorting, Geometry
// Time: O(n log n)
// Space: O(n)

public class CountCoveredBuildings_3531 {

    public int countCoveredBuildings(int n, int[][] buildings) {

        // Map: x-coordinate -> list of y-coordinates
        Map<Integer, List<Integer>> buildingsAtX = new HashMap<>();
        // Map: y-coordinate -> list of x-coordinates
        Map<Integer, List<Integer>> buildingsAtY = new HashMap<>();

        // Group buildings by x and y
        for (int[] building : buildings) {
            int x = building[0];
            int y = building[1];

            buildingsAtX.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
            buildingsAtY.computeIfAbsent(y, k -> new ArrayList<>()).add(x);
        }

        // Sort coordinates for min/max checks
        for (List<Integer> list : buildingsAtX.values()) {
            Collections.sort(list);
        }
        for (List<Integer> list : buildingsAtY.values()) {
            Collections.sort(list);
        }

        int coveredCount = 0;

        // Check each building
        for (int[] building : buildings) {
            int x = building[0];
            int y = building[1];

            List<Integer> sameX = buildingsAtX.get(x);
            List<Integer> sameY = buildingsAtY.get(y);

            boolean hasBelow = sameX.get(0) < y;
            boolean hasAbove = y < sameX.get(sameX.size() - 1);
            boolean hasLeft  = sameY.get(0) < x;
            boolean hasRight = x < sameY.get(sameY.size() - 1);

            if (hasBelow && hasAbove && hasLeft && hasRight) {
                coveredCount++;
            }
        }

        return coveredCount;
    }
}
