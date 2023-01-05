class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        n = len(s)
        m = len(p)
        dp = [[None for j in range(n + 1)] for i in range(m + 1)]
        
        for j in range(n):
            dp[m][j] = False
            
        dp[m][n] = True
        
        def dfs(i, j):
            if dp[i][j] == False or dp[i][j] == True :
                return dp[i][j]
            
            match1 = i+1 < m and p[i+1] == '*'
            if j==n:
                dp[i][j] =  dfs(i+2, j) if match1 else False                
                return dp[i][j]
                
        
            dp[i][j] = False
            match2 = s[j] == p[i] or p[i] == '.'
            # *Matches zero element or more element.
            if  match1:
                dp[i][j] =  dfs(i+2, j) or ( match2 and  dfs(i, j + 1))          
                return  dp[i][j]
                     
            if match2:
                 dp[i][j] =  dfs(i+1, j+1)
            
            return dp[i][j]   
            
        return dfs(0,0)