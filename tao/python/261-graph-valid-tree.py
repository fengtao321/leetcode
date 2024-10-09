class Solution:
    def validTree(self, n: int, edges: List[List[int]]) -> bool:
        uf = UnionFind(n)
        for edge in edges:
            if uf.union(edge[0], edge[1]) == False:
                return False
            
        return uf.isTree()

class UnionFind:
    def __init__(self, N) -> None:
        self.parent = [i for i in range(N)]
        self.rank = [1] * N
        
    def find(self, num):
        if self.parent[num] == num:
            return num
        return self.find(self.parent[num])
    
    def union(self,u, v):
        pu, pv = self.find(u), self.find(v)
        if pu == pv:
            return False

        if self.rank[pu] >= self.rank[pv]:
            self.parent[pv] = pu
            self.rank[pu] +=self.rank[pv]
        else:
            self.parent[pu] = pv
            self.rank[pv] +=self.rank[pu]
        return True
        
    
    def isTree(self):
        print(self.parent)
        print(self.rank)
        root = 0
        for i in range(len(self.parent)):
            if self.parent[i] == i:
                root += 1
                if root > 1:
                    return False
                
        return True