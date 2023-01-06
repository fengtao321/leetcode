class Solution:
    def minimumObstacles(self, grid: List[List[int]]) -> int:
        m = len(grid)
        n = len(grid[0])
        
        Point = namedtuple('Point', ['cost','x', 'y'])
        directions = [[-1,0],[1, 0],[0,-1],[0,1]]
        visited = set()
        
        queue = [Point(grid[m-1][n-1], m-1, n-1)]
        heapq.heapify(queue)
        while queue:
            p = heapq.heappop(queue)
            if p.x == 0 and p.y==0:
                return p.cost
            
            for direction in directions:
                x = p.x + direction[0]
                y = p.y + direction[1]
                
                if x>-1 and y>-1 and x<m and y<n and (x,y) not in visited:
                    heapq.heappush(queue, Point(p.cost + grid[x][y], x, y))
                    visited.add((x,y))
            

        return 0