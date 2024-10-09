class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        if len(nums) == 0:
            return 0;
        
        uf = UnionFind(len(nums))
        hashmap =  {nums[i]: i for i in range(len(nums))}
        
        for i in range(len(nums)):
            num = nums[i]
            if hashmap[num] != i or  num-1 not in hashmap:
                continue

            uf.union(i, hashmap[num-1])
        return uf.max_rank()

class UnionFind:
    def __init__(self, N) -> None:
        self.parent = [i for i in range(N)]
        self.rank = [1] * N
        self.rank_max = 1
        
    def find(self, num):
        if self.parent[num] == num:
            return num
        return self.find(self.parent[num])
    
    def union(self, u, v):
        parent1, parent2 = self.find(u), self.find(v)        
        rank1, rank2 = self.rank[parent1], self.rank[parent2] 
            
        if rank1 < rank2:
            self.parent[parent1] = parent2
            self.rank[parent2] += self.rank[parent1]
        else:
            self.parent[parent2] = parent1
            self.rank[parent1] += self.rank[parent2]
        self.rank_max = max(self.rank_max, self.rank[parent2], self.rank[parent1])
        # print(self.parent)
        # print(self.rank)
        # if rank1 == rank2:
        #     self.rank[parent1] +=1
    
    def connected(self, u, v):
        return self.find(u) == self.find(v)  
    
    def max_rank(self):
        return self.rank_max
        