class Solution:
    def takeCharacters(self, s: str, k: int) -> int:
        counter = {c: 0 for c in ['a','b','c']}                
        for c in s:
            counter[c]+=1
        
        if counter['a']<k or counter['b']<k or counter['c']<k :
            return -1
        
        l = 0
        size = 0
        for r in range(len(s)):
            c = s[r]
            counter[c] -= 1
            while counter[c]<k:
                # move left
                counter[s[l]] += 1
                l +=1 

            size = max(size, r - l + 1) 
               
        return len(s) - size

            
        