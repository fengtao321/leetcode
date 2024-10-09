class Solution:
    def findCheapestPrice(self, n: int, flights: List[List[int]], src: int, dst: int, k: int) -> int:
        graph = { c: [] for c in range(n)}
        dp = [-1] * n
        for flight in flights:
            graph[flight[0]].append(flight)
        
        queue = deque()
        queue.append((src, 0))
        while queue and k>-2:    
            size = len(queue)
            k = k-1
            for i in range(size):
                curr = queue.popleft()
                dp[curr[0]] =  curr[1] if dp[curr[0]]==-1 else  min(dp[curr[0]], curr[1])
                if curr[0]==dst:
                    continue
                
                for flight in graph[curr[0]]:
                    if dp[flight[1]] == -1 or flight[2]+curr[1] < dp[flight[1]]:
                        queue.append((flight[1], flight[2]+curr[1]))
        
        return dp[dst]
            