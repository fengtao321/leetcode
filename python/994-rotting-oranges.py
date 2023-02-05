class Solution:
    def orangesRotting(self, grid: List[List[int]]) -> int:
        queue, fresh_orange, m, n = deque([]), 0, len(grid), len(grid[0])
        directions = [(0,1),(0,-1),(1,0),(-1,0)]
        for i in range(m):
            for j in range(n):
                if grid[i][j]==2:
                    queue.append((i, j))
                if grid[i][j]==1:
                    fresh_orange+=1
                    
        time = -1 if queue else 0
        while queue:
            size, time = len(queue), time+1
            for _ in range(size):
                (cx,cy) = queue.popleft()
                for (dx, dy) in directions:
                    x, y = cx+dx, cy+dy
                    if x>=0 and y>=0 and x<m and y<n and grid[x][y]==1:
                        grid[x][y]=2
                        fresh_orange-=1
                        queue.append((x,y))
                        
        return time if fresh_orange == 0 else -1
            
                
        