from functools import lru_cache


class Solution:
    def minHeightShelves(self, books: List[List[int]], shelfWidth: int) -> int:
        @lru_cache(maxsize=None)
        def dfs(i):
            if i>=len(books): return 0
            
            record = float('inf')
            height, width = books[i][1], 0

            while i<len(books) and width+books[i][0]<=shelfWidth:
                if books[i][1]>height:
                    record = min(record, height+dfs(i))
                    height = books[i][1]
                
                width+=books[i][0]
                i+=1
            
            
            return min(record,  height + dfs(i))
        return dfs(0)
            
            
        