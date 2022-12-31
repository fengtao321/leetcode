class Solution {
    char[] s1Arr;
    char[] s2Arr;
    char[] s3Arr;
    int[][] dp;

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length())
            return false;

        s1Arr = s1.toCharArray();
        s2Arr = s2.toCharArray();
        s3Arr = s3.toCharArray();

        dp = new int[s1Arr.length][s2Arr.length]; // s1 index or s2 index
        return dfs(0, 0, 0);
    }

    private boolean dfs(int s1i, int s2i, int s3i) {
        if (s1i == s1Arr.length && s2i == s2Arr.length) {
            return true;
        }

        if (dp[s1i][s2i] < 0)
            return false;

        if (dp[s1i][s2i] > 0)
            return true;

        if (s1i < s1Arr.length && (s3Arr[s3i] == s1Arr[s1i]) && dfs(s1i + 1, s2i, s3i + 1)) {
            dp[s1i][s2i] = 1;
            return true;
        }

        if (s2i < s2Arr.length && (s3Arr[s3i] == s2Arr[s2i]) && dfs(s1i, s2i + 1, s3i + 1)) {
            dp[s1i][s2i] = 1;
            return true;
        }

        dp[s1i][s2i] = -1;
        return false;
    }
}