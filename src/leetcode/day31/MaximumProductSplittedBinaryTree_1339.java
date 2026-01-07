package leetcode.day31;

// LeetCode 1339: Maximum Product of Splitted Binary Tree
// Difficulty: Medium
// Topic: Tree, DFS
// Time: O(n)
// Space: O(h)

public class MaximumProductSplittedBinaryTree_1339 {

    private long totalSum = 0;
    private long maxProduct = 0;
    private static final int MOD = 1_000_000_007;

    public int maxProduct(TreeNode root) {
        totalSum = calculateSum(root);
        findMaxProduct(root);
        return (int) (maxProduct % MOD);
    }

    private long calculateSum(TreeNode node) {
        if (node == null) return 0;
        return node.val
                + calculateSum(node.left)
                + calculateSum(node.right);
    }

    private long findMaxProduct(TreeNode node) {
        if (node == null) return 0;

        long subTreeSum =
                node.val
                        + findMaxProduct(node.left)
                        + findMaxProduct(node.right);

        long product = subTreeSum * (totalSum - subTreeSum);
        maxProduct = Math.max(maxProduct, product);

        return subTreeSum;
    }
}
