import heapq


class Solution:
    def minCostConnectPoints(self, points: List[List[int]]) -> int:
        n = len(points)
        graph = {c: [] for c in range(n)}
        
        for i in range(n):
            for j in range(i+1, n):
                dist = abs(points[j][1] - points[i][1]) + abs(points[j][0] - points[i][0])
                graph[i].append([dist, j])
                graph[j].append([dist, i])

        visited = set()
        queue = [[0,0]]
        heapq.heapify(queue)
        ans = 0
        while queue:
            p1 = heapq.heappop(queue)
            if p1[1] in visited:
                continue
                
            visited.add(p1[1])
            ans += p1[0]
            if len(visited)==n:
                break
            
            for p2 in graph[p1[1]]:
                if p2[1] not in visited:
                   heapq.heappush(queue, [p2[0],p2[1]])
        return ans