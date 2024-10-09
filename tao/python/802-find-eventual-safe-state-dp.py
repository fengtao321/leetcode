class Solution:
    def eventualSafeNodes(self, graph: List[List[int]]) -> List[int]:
        N = len(graph)
        dp = [-1] * N
        def dfs(x):
            if dp[x] != -1: return dp[x]
            dp[x] = 0
            for i in graph[x]:
                if dfs(i) == 0:
                    return 0
            dp[x] = 1
            return 1
        return [i for i in range(N) if dfs(i)]