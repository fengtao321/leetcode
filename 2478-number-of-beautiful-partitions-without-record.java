class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int beautifulPartitions(String S, int k, int l) {
        var s = S.toCharArray();
        var n = s.length;
        if (k * l > n || !isPrime(s[0]) || isPrime(s[n - 1])) // 剪枝
            return 0;
        var f = new int[k + 1][n + 1];
        f[0][0] = 1;
        for (var i = 1; i <= k; ++i) {
            var sum = 0;
            // 优化：枚举的起点和终点需要给前后的子串预留出足够的长度
            for (var j = i * l; j + (k - i) * l <= n; j++) {
                if (canPartition(s, j - l))
                    sum = (sum + f[i - 1][j - l]) % MOD; // j'=j-l 双指针
                if (canPartition(s, j))
                    f[i][j] = sum;
            }
        }
        return f[k][n];
    }

    private boolean isPrime(char c) {
        return c == '2' || c == '3' || c == '5' || c == '7';
    }

    // 判断是否可以在 j-1 和 j 之间分割（开头和末尾也算）
    private boolean canPartition(char[] s, int j) {
        return j == 0 || j == s.length || !isPrime(s[j - 1]) && isPrime(s[j]);
    }
}