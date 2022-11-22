public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return max;
    }

    private int dfs(TreeNode node) {
        if (node == null)
            return 0;

        int left = dfs(node.left);
        int right = dfs(node.right);
        if (left < 0)
            left = 0; // <---- this is important, we don't want to compare several times
        if (right < 0)
            right = 0;

        int sum = left + right + node.val;
        max = Math.max(max, sum);

        return Math.max(left + node.val, right + node.val);
    }
}
/**
 * 
 * slow solution
 */
// class Solution {
// int max = Integer.MIN_VALUE;

// public int maxPathSum(TreeNode root) {
// dfs(root);
// return max;
// }

// private int dfs(TreeNode node) {
// if (node == null)
// return 0;

// int left = dfs(node.left);
// int right = dfs(node.right);

// int sum = left + right + node.val;
// max = max(max, sum, left, right); //<----slow

// return Math.max(Math.max(left, right) + node.val, node.val);
// }

// private int max(Integer... vals) {
// return Collections.max(Arrays.asList(vals));
// }
// }