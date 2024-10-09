class Solution:
    def eventualSafeNodes(self, graph: List[List[int]]) -> List[int]:
        connections = defaultdict(list)
        ranks =  [0] * (len(graph)); 
        
        for i in range(len(graph)):
            ranks[i] = len(graph[i])
            for n in graph[i]:
                connections[n].append(i)

        
        queue = deque(idx for idx, prereq in enumerate(ranks) if prereq == 0)
        ans = []
        while (queue) :
            n = queue.popleft()
            ans.append(n)
        
            for fromN in connections[n]:                
                ranks[fromN] -= 1
                if(ranks[fromN] == 0):
                    queue.append(fromN)

        return sorted(ans)
        
               