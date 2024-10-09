class Solution:
    def longestIncreasingPath(self, matrix: List[List[int]]) -> int:
        m = len(matrix)
        n = len(matrix[0])
        cache = [[-1 for j in range(m)] for i in range(n)]
        directions = [[1,0],[-1,0],[0,-1],[0,1]]

        def dfs(i, j, target) -> int:
            if i==-1 or j==-1 or i==m or j==n or matrix[i][j]<=target:
                return 0
            
            if cache[i][j]>-1:
                return cache[i][j]
            
            for direction in directions:
                cache[i][j] = 1 + max(dfs(i-1, j, matrix[i][j]), dfs(i+1,j, matrix[i][j]),dfs(i, j-1, matrix[i][j]), dfs(i, j+1, matrix[i][j]))
            return  cache[i][j]
            
        ans = 1;
        for i in range(m):
            for j in range(n):
                ans= max(dfs(i, j, -1), ans)
                
        return ans
    