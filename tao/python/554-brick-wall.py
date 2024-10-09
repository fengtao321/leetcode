class Solution:
    def leastBricks(self, wall: List[List[int]]) -> int:
        hashmap = defaultdict(int)
        
        for row in wall:
            key = 0
            for i in range(len(row)-1):
                key += row[i]
                hashmap[key] += 1 

        if not hashmap:
            return len(wall)
        
        return len(wall) - max(hashmap.values())
        
        
                