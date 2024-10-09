from functools import lru_cache

class Solution:
    def minimumChanges(self, s: str, k: int) -> int:
        N = len(s)    
        def gerRecord():
            matrix =  [[N] * N for _ in range(N)]
            for fr in range(0, N):
                for to in range(fr+2, N):
                    matrix[fr][to] = getMinChanges(fr, to)
            return matrix
        
        def getMinChanges(fr, to):
            curr_str = []
            curr_min_changes = to-fr
            for div in divisors[to-fr]:
                for i in range(fr, to, div):
                    curr_str.append(s[i])
                    
                curr_changes = 0
                for i in range(len(curr_str)//2):
                    if curr_str[i] != curr_str[to-fr-i-1]: curr_changes+=1
                   
                curr_min_changes = min(curr_min_changes, curr_changes)
            return curr_min_changes
            
        
        def getDivisor(length):
            possible_divisors = [[] for _ in range(length+1)]

            for l in range(2, length+1):
                for divisor in range (1, 1+l//2):
                    if l%divisor == 0: possible_divisors[l] .append(divisor)
            return possible_divisors
        

        divisors = getDivisor(N)
        print("divisors = ", divisors)
        record = gerRecord()
        print("record = ", record)
        
        @lru_cache(None)
        def dfs(index, curr_k):
            if curr_k == 0: return 0
            curr = N
            
            for i in range(index+2, N):
                curr = min(curr, record[index][i] + dfs(i, curr_k-1))
            return curr
        
        return dfs(0, k)
    
c = Solution()
print(c.minimumChanges("abcac", 2))
print(c.minimumChanges("abcdef", 2))
print(c.minimumChanges("aabbaa", 3))
# assert c.minimumChanges("abcac", 2) == 1
# assert c.minimumChanges("abcdef", 2) == 2
# assert c.minimumChanges("aabbaa", 3) == 0