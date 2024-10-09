class Solution:
    def gridGame(self, grid: List[List[int]]) -> int:
        n = len(grid[0])
        pre_sum, post_sum = [0] * n, [0] * n
        post_sum[0] = sum(grid[0])- grid[0][0]
        
        ans = max(pre_sum[0], post_sum[0])
        for i in range(1, n):
           pre_sum[i] = pre_sum[i-1] + grid[1][i-1]
           post_sum[i] = post_sum[i-1] - grid[0][i]
           ans = min(ans, max(pre_sum[i],  post_sum[i]))
                    
        return ans
