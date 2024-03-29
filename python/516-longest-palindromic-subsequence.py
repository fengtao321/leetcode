class Solution:
    def longestPalindromeSubseq(self, s: str) -> int:
        n = len(s)
        dp = [[0] * n for _ in range(n)]
        max_num = 1
        for i in range(n-1, -1, -1):
            dp[i][i] = 1
            for j in range(i+1, n):
                dp[i][j] = dp[i+1][j-1] +2 if s[i]==s[j] else max(dp[i+1][j],dp[i][j-1])
                max_num = max (dp[i][j], max_num)
        # print(dp)
        return max_num
    
                
        