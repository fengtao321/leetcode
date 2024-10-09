class Solution:
    def cherryPickup(self, grid: List[List[int]]) -> int:
        m, n, ans = len(grid), len(grid[0]),0
        last_row = defaultdict(int) #sum, left j, right j
        last_row[(0, n-1)] = grid[0][0]+grid[0][n-1]
        directions = [-1, 0, 1]
        
        for i in range(1, m):
            curr_row = defaultdict(int)
            for (l,r),cherry  in last_row.items():
                for direction in directions:
                    curr_l = l + direction
                    if l + direction == -1 or l + direction ==n:
                        continue
                   
                    for direction in directions:
                        curr_r = r + direction
                        if curr_r == -1 or curr_r ==n or curr_r == curr_l:
                            continue
                        k = ((min(curr_l, curr_r), max(curr_l,curr_r)))
                        curr_row[k] =  max(curr_row[k], cherry+grid[i][curr_l]+grid[i][curr_r])
                        ans = max(ans, curr_row[k])
            last_row = curr_row

        return ans