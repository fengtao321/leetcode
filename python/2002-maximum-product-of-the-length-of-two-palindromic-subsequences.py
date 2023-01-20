class Solution:
    def maxProduct(self, s: str) -> int:
        n, hashmap = len(s), {}
        
        for i in range(1, 1<<n):
            sub_str = ""
            for j in range(n):
                if (i & (1<<j)) > 0:
                    sub_str += s[j]
                    
            if sub_str == sub_str[::-1]:
               hashmap[i] = len(sub_str)
         
        ans = 0
        for x in hashmap:
            for y in hashmap:
                if (x&y) == 0:
                    ans = max(ans,hashmap[x]*hashmap[y])
        
        return ans
            