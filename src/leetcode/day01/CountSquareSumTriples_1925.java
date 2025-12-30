package leetcode.day01;

import java.util.*;

// LeetCode 1925: Count Square Sum Triples
// Difficulty: Easy
// Link: https://leetcode.com/problems/count-square-sum-triples/

public class CountSquareSumTriples_1925 {

    public int countTriples(int n) {
        int ans = 0;
        Set<Integer> squared = new HashSet<>();

        for (int i = 1; i <= n; i++) {
            squared.add(i * i);
        }

        for (int a : squared) {
            for (int b : squared) {
                if (squared.contains(a + b)) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
