class Solution:
    def minFlips(self, mat: List[List[int]]) -> int:
        n,m, total_one = len(mat[0]), len(mat),0
        directions = [[0, 0], [0, 1], [0, -1], [1, 0], [-1, 0]]
        ans = m*n+1
        for i in range(m):
            for j in range(n):
                if mat[i][j]==1:
                    total_one +=1
        
        def flipCell(i, j):
            nonlocal mat
            mat[i][j] = 0 if mat[i][j] == 1 else 1
            return 1 if mat[i][j] == 1 else -1
                    
        def flip(i, j):
            flipped_one = 0
            for direction in directions:
                x, y = i + direction[0],j + direction[1]
                if x>=0 and y>=0 and x<m and y<n:
                    flipped_one += flipCell(x,y)
            return flipped_one
        
        def dfs(i, j, total_one, counter):
            nonlocal ans
            if j==n:
                return dfs(i+1, 0, total_one, counter)
            if total_one == 0:
                ans = min(ans, counter)
                return True
            if i==m:
                return False
            
            if dfs(i, j+1, total_one, counter):
                return True
            
            total_one += flip(i, j)
            if dfs(i, j+1, total_one, counter+1):
                return True
            total_one -= flip(i, j)
            return False
        
        dfs(0, 0, total_one, 0)
        return ans if ans<m*n+1 else -1