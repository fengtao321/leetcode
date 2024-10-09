# https://leetcode.cn/problems/sum-of-distances-in-tree/solution/shou-hua-tu-jie-shu-zhong-ju-chi-zhi-he-shu-xing-d/
from collections import defaultdict


class Solution:
    def sumOfDistancesInTree(self, n: int, edges: List[List[int]]) -> List[int]:        
        def childSum(node, parent):
            value,  counter = 0, 1
            for nxt in graph[node]:
                if nxt!=parent:
                    childSum(nxt, node)
                    value+=counters[nxt] + values[nxt]
                    counter+=counters[nxt] 
            
            counters[node] = counter
            values[node] = value
       

    
        def dfs(node, parent):
            for nxt in graph[node]:
                if nxt!=parent:
                    ans[nxt] = ans[node] - counters[nxt] + (n-counters[nxt])
                    dfs(nxt, node)
        
        
        graph = defaultdict(list)
        for x, y in edges:
            graph[x].append(y)
            graph[y].append(x)
        
        values = [0]*n
        counters = [0]*n
        childSum(0, -1)
        
        ans = [0] * n
        ans[0] = values[0]
        dfs(0, -1)
        return ans