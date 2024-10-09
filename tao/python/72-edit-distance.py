class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        l1 = len(word1)
        l2 = len(word2)
        dp = [[-1 for j in range(l1 + 1)] for i in range(l2 + 1)]
        
        for j in range(l1 + 1):
            dp[l2][j] = l1 - j
        
        for i in range(l2 + 1):
            dp[i][l1] = l2 - i

        def dfs(i, j):
            if dp[i][j] > -1:
                return dp[i][j]
            
            if word2[i] == word1[j]:
                return dfs(i+1, j+1)
            
            # dfs(i + 1, j) <--- insert
            # dfs(i, j + 1) <--- delete
            # dfs(i + 1, j + 1) <--- replace
            dp[i][j] = 1+ min(dfs(i + 1, j), dfs(i, j+1), dfs(i+1, j+1))
            return dp[i][j]
            
        return dfs(0, 0)
        