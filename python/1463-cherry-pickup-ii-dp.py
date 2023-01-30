class Solution:
    def cherryPickup(self, grid: List[List[int]]) -> int:
        m, n, directions = len(grid), len(grid[0]), [-1, 0, 1]
        
        @lru_cache(maxsize=None)
        def dfs(row, left, right):
            if row == m:
                return 0
            
            curr_cherry = 0
            for dl in directions:
                l = left + dl
                if l<0 or l== n:
                   continue
                
                for dr in directions:
                    r = right + dr
                    if r<0 or r== n or l==r:
                        continue
                    curr_cherry = max(curr_cherry, dfs(row+1, min(l, r), max(l, r)))
            return curr_cherry + grid[row][left] + grid[row][right]
        return dfs(0, 0, n-1)
                    