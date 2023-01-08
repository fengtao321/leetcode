from collections import deque


class Solution:
    def findItinerary(self, tickets: List[List[str]]) -> List[str]:
        graph = defaultdict(list)
        tickets.sort()     
        for ticket in tickets:
            graph[ticket[0]].append(ticket[1])
            
        str = []
        def dfs(ticket, counter):
            str.append(ticket)
            if counter==0:
                return True
            
            if ticket not in graph:
                str.pop(-1)
                return False
            
            # str.append(ticket)
            temp = graph[ticket]
            result = False
            for i, v in  enumerate(temp):
                graph[ticket].pop(i)
                if dfs(v, counter-1) == True:
                    result = True
                    return True
                graph[ticket].insert(i, v)
            
            str.pop(-1)
            return False
                
        
        dfs("JFK", len(tickets))
        return str

            