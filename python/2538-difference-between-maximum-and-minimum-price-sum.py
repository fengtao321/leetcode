class Solution:
    def maxOutput(self, n: int, edges: List[List[int]], price: List[int]) -> int:
        graph = defaultdict(list)
        for edge in edges:
            graph[edge[0]].append(edge[1])
            graph[edge[1]].append(edge[0])
        
        ans = 0
        @lru_cache(maxsize=None)
        def dfs(curr, parent):
            nonlocal ans
            max_s1 = price[curr]
            max_s2 = 0
        
            for next_node in graph[curr]:
                if next_node != parent:
                    s1, s2 = dfs(next_node, curr)
                    ans = max(ans, s1+max_s2, s2+max_s1)
                    max_s1 = max(s1 +price[curr], max_s1)
                    max_s2 = max(s2+price[curr], max_s2)

            return max_s1, max_s2
        
        dfs(0, -1)
        return ans

        