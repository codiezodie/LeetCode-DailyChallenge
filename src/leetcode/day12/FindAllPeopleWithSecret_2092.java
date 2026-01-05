package leetcode.day12;

import java.util.*;

// LeetCode 2092: Find All People With Secret
// Difficulty: Hard
// Topic: Union Find, Sorting
// Time: O(m log m)
// Space: O(n)

public class FindAllPeopleWithSecret_2092 {

    int[] parent;
    int[] rank;

    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {

        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // Person 0 and firstPerson know the secret at time 0
        union(0, firstPerson);

        // Sort meetings by time
        Arrays.sort(meetings, Comparator.comparingInt(m -> m[2]));

        int m = meetings.length;
        for (int i = 0; i < m; ) {

            int time = meetings[i][2];
            Set<Integer> peopleInThisTime = new HashSet<>();

            // Union all meetings happening at the same time
            while (i < m && meetings[i][2] == time) {
                int x = meetings[i][0];
                int y = meetings[i][1];
                union(x, y);
                peopleInThisTime.add(x);
                peopleInThisTime.add(y);
                i++;
            }

            // Reset people who are not connected to person 0
            for (int p : peopleInThisTime) {
                if (find(p) != find(0)) {
                    parent[p] = p;
                    rank[p] = 0;
                }
            }
        }

        // Collect all people who know the secret
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (find(i) == find(0)) {
                result.add(i);
            }
        }

        return result;
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    private void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }
}
