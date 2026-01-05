package leetcode.day09;

import java.util.*;

// LeetCode 3562: Max Profit With Hierarchy
// Topic: Tree DP, Knapsack DP
// Difficulty: Hard

public class MaxProfitWithHierarchy_3562 {

    int n, budget;
    int[] present, future;
    List<Integer>[] tree;
    Map<Long, DPState> memo = new HashMap<>();

    static class DPState {
        int[] noDiscount;
        int[] withDiscount;

        DPState(int[] a, int[] b) {
            noDiscount = a;
            withDiscount = b;
        }
    }

    public int maxProfit(int n, int[] present, int[] future,
                         int[][] hierarchy, int budget) {

        this.n = n;
        this.present = present;
        this.future = future;
        this.budget = budget;

        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int[] e : hierarchy) {
            int u = e[0] - 1;
            int v = e[1] - 1;
            tree[u].add(v);
        }

        DPState res = dp(0, -1);
        int ans = 0;
        for (int val : res.noDiscount) {
            ans = Math.max(ans, val);
        }
        return ans;
    }

    private DPState dp(int u, int parent) {
        long key = (((long) u) << 32) | (parent + 1);
        if (memo.containsKey(key)) return memo.get(key);

        int[] noDiscount = new int[budget + 1];
        int[] withDiscount = new int[budget + 1];

        for (int v : tree[u]) {
            if (v == parent) continue;
            DPState child = dp(v, u);
            noDiscount = merge(noDiscount, child.noDiscount);
            withDiscount = merge(withDiscount, child.withDiscount);
        }

        int[] newDp0 = noDiscount.clone();
        int[] newDp1 = noDiscount.clone();

        // Buy at full cost
        int fullCost = present[u];
        for (int b = fullCost; b <= budget; b++) {
            int profit = future[u] - fullCost;
            newDp0[b] = Math.max(newDp0[b],
                    withDiscount[b - fullCost] + profit);
        }

        // Buy at half cost (discount)
        int halfCost = present[u] / 2;
        for (int b = halfCost; b <= budget; b++) {
            int profit = future[u] - halfCost;
            newDp1[b] = Math.max(newDp1[b],
                    withDiscount[b - halfCost] + profit);
        }

        DPState state = new DPState(newDp0, newDp1);
        memo.put(key, state);
        return state;
    }

    private int[] merge(int[] A, int[] B) {
        int[] res = new int[budget + 1];
        Arrays.fill(res, Integer.MIN_VALUE);

        for (int i = 0; i <= budget; i++) {
            if (A[i] == Integer.MIN_VALUE) continue;
            for (int j = 0; i + j <= budget; j++) {
                if (B[j] == Integer.MIN_VALUE) continue;
                res[i + j] = Math.max(res[i + j], A[i] + B[j]);
            }
        }
        return res;
    }
}
