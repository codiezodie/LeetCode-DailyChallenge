package leetcode.day19;

import java.util.*;

// LeetCode 2483: Minimum Penalty for a Shop
// Difficulty: Medium
// Topic: Prefix Sum, Greedy
// Link: https://leetcode.com/problems/minimum-penalty-for-a-shop/

public class MinimumPenaltyForShop_2483 {

    public int bestClosingTime(String customers) {
        int n = customers.length();

        // Count total 'Y'
        int totalY = 0;
        for (int i = 0; i < n; i++) {
            if (customers.charAt(i) == 'Y') {
                totalY++;
            }
        }

        int penalty = Integer.MAX_VALUE;
        int best = 0;

        int leftN = 0;      // penalties from 'N' before closing
        int rightY = totalY; // penalties from 'Y' after closing

        for (int j = 0; j <= n; j++) {
            int currentPenalty = leftN + rightY;

            if (currentPenalty < penalty) {
                penalty = currentPenalty;
                best = j;
            }

            if (j < n) {
                if (customers.charAt(j) == 'Y') {
                    rightY--;
                } else {
                    leftN++;
                }
            }
        }

        return best;
    }
}
