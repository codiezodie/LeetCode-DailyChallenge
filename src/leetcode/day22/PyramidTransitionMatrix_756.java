package leetcode.day22;

import java.util.*;

// LeetCode 756: Pyramid Transition Matrix
// Difficulty: Medium
// Topic: DFS, Backtracking, HashMap
// Link: https://leetcode.com/problems/pyramid-transition-matrix/

public class PyramidTransitionMatrix_756 {

    public boolean pyramidTransition(String bottom, List<String> allowed) {

        Map<String, List<Character>> prefixToBlocks = new HashMap<>();

        // Build mapping: "AB" -> ['C', 'D', ...]
        for (String a : allowed) {
            String lowerBlocks = a.substring(0, 2);
            prefixToBlocks.putIfAbsent(lowerBlocks, new ArrayList<>());
            prefixToBlocks.get(lowerBlocks).add(a.charAt(2));
        }

        return dfs(bottom, "", 0, prefixToBlocks);
    }

    private boolean dfs(String row, String nextRow, int i,
                        Map<String, List<Character>> prefixToBlocks) {

        // Successfully built pyramid
        if (row.length() == 1)
            return true;

        // Move to next level
        if (nextRow.length() + 1 == row.length())
            return dfs(nextRow, "", 0, prefixToBlocks);

        String prefix = row.substring(i, i + 2);

        if (!prefixToBlocks.containsKey(prefix))
            return false;

        for (char c : prefixToBlocks.get(prefix)) {
            if (dfs(row, nextRow + c, i + 1, prefixToBlocks))
                return true;
        }

        return false;
    }
}
