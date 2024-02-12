from functools import lru_cache

class Solution:
    def minOperations(self, s1: str, s2: str, x: int) -> int:
        @lru_cache(None)
        def dfs(i, wait_to_processed):
            if i==N: return 0 if wait_to_processed==0 else float('inf')
            if l1[i] == l2[i]: return dfs(i+1, wait_to_processed)
            
            curr = float('inf')
            if i<N-1:
                l1[i+1] = (l1[i+1]+1) % 2
                curr = 1 + dfs(i+1, wait_to_processed)
                l1[i+1] = (l1[i+1]+1) % 2
            
            if wait_to_processed == 0:
                curr = min(curr, dfs(i+1, wait_to_processed+1))
            else:
                curr = min(curr, x+ dfs(i+1, wait_to_processed-1))
                
            return curr
                
        
        l1, l2 =  [int(i) for i in s1], [int(i) for i in s2]
        N = len(l1)
        
        ans = dfs(0, 0)
        return -1 if ans==float('inf') else ans
    
    

c = Solution()
print(c.minOperations("1100011000", "0101001010", 2))
print(c.minOperations("10110", "00011", 4))
assert c.minOperations("1100011000", "0101001010", 2) == 4
assert c.minOperations("10110", "00011", 4) == -1
assert c.minOperations("101101", "000000", 6) == 4
