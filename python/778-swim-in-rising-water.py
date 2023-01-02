from collections import namedtuple
import heapq


class Solution:
    def swimInWater(self, grid: List[List[int]]) -> int:
        n = len(grid)
        Point = namedtuple('Point', ['height','x', 'y'])
        
        minHeap = [Point(grid[0][0], 0, 0)]
        heapq.heapify(minHeap)
        ans = grid[0][0]
        visited = set()
        
        while(minHeap):
            p = heapq.heappop(minHeap)
            ans = max(ans, p.height)
            if p.x==n-1 and p.y==n-1:
                break
            
            if p.x>0:
                up = Point(grid[p.x-1][p.y],p.x-1, p.y)
                if up not in visited:
                    heapq.heappush(minHeap, up)
                    visited.add(up)
            
            
            if p.y>0:
                left = Point(grid[p.x][p.y-1],p.x, p.y-1)
                if left not in visited:
                    heapq.heappush(minHeap, left)
                    visited.add(left)
            
            if p.x<n-1:
                down = Point(grid[p.x+1][p.y],p.x+1, p.y)
                if down not in visited:
                    heapq.heappush(minHeap, down)
                    visited.add(down)
            
            if p.y<n-1:
                right = Point(grid[p.x][p.y+1],p.x, p.y+1)
                if right not in visited:
                    heapq.heappush(minHeap, right)
                    visited.add(right)
            
        return ans