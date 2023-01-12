class Solution:
    def findAllPeople(self, n: int, meetings: List[List[int]], firstPerson: int) -> List[int]:
        meetings.sort(key=lambda x:x[2])
        parent = [i for i in range(n)]
        rank = [0] * n
        
        def findParent(i):
            if parent[i] == i:
                return i
            return findParent(parent[i])
        
        def unionNodes(i, j):
            rootx =  parent[i]
            rooty =  parent[j]
            
            if rootx!=rooty:
                if rank[rootx]<rank[rooty]:
                    rootx,rooty = rooty,rootx
                    
                if rank[rootx] == rank[rooty]:
                    rank[rootx] += 1
                
                parent[rooty] = rootx

            
        last = 0
        queue = set()
        unionNodes(0, firstPerson)
        rank[0] = n
        ans = set([0, firstPerson])
        for meeting in meetings:
            if meeting[2]!= last:
                for i in queue:
                    if findParent(i) in ans:
                        ans.add(i)
                    else:
                        parent[i] = i
                        rank[i] = 0
                
                last = meeting[2]
                queue.clear()
            
            unionNodes(meeting[0],meeting[1])

            if meeting[0] not in ans:
                queue.add(meeting[0])
            if meeting[1] not in ans:
                queue.add(meeting[1])

        for i in queue:
            if findParent(i) in ans:
                ans.add(i)
                            
        return list(ans)