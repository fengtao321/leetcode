class Solution:
    def minWindow(self, s: str, t: str) -> str:
        hashmap = defaultdict(int)
        target = defaultdict(int)
        for c in t:
            hashmap[c] += 1
        
        l = 0
        conditions = len(hashmap)
        ans = s+t  
        for r in range(len(s)):
            c = s[r]
            if c not in hashmap:
                continue
            
            target[c] += 1
            if target[c] == hashmap[c]:
                conditions -=1
        
                while conditions == 0:
                    # move left
                    if r-l+1<len(ans):
                        ans = s[l:r+1]
                    c = s[l]
                    l+=1
                    
                    if c in hashmap:
                        if target[c] == hashmap[c]:
                            conditions +=1
                        target[c] -= 1
        
        return ans if len(ans)<=len(s) else ""
            