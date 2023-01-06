from collections import namedtuple


class Solution:
    def shortestPath(self, grid: List[List[int]], k: int) -> int:
        m = len(grid)
        n = len(grid[0])
        k = k - grid[0][0] - grid[m-1][n-1]

        directions = [[-1,0],[1, 0],[0,-1],[0,1]]

        queue = [[0, m-1, n-1]] # cost, x , y 
        step = -1
        
        while(queue):
            if step == m*n:
                return -1
            
            visited = {}
            l = len(queue)
            step += 1
            for i in range(l):
                p = queue.pop(0)
                if p[1] == 0 and p[2] == 0:
                    if p[0] <= k:
                        return step
                    continue
                
                for direction in directions:
                    x = p[1] + direction[0]
                    y = p[2] + direction[1]
                    if x ==m-1 and n==n-1:
                        continue
                    
                    if x>-1 and y>-1 and x<m and y<n and p[0] + grid[x][y] <=k:
                        if (x,y) not in visited:
                            visited[(x,y)] = [p[0] + grid[x][y], x, y]
                            queue.append(visited[(x,y)])
                        else:
                            nextP = visited[(x,y)]
                            nextP[0] = min( nextP[0], p[0] + grid[x][y])
                            
        return -1