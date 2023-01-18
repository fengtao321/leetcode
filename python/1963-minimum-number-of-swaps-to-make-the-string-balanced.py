class Solution:
    def minSwaps(self, s: str) -> int:
        counter, l, r = 0, 0, len(s)-1
        ans = 0
        while l<r:
            if s[l] == "[":
                l += 1
                counter += 1
                continue
                
            if  s[l] == "]" and counter > 0:
                l += 1
                counter -=1
                continue
            
            while s[r] == "]":
                r -= 1
            ans+=1
            l +=1
            r -= 1
            counter += 1
        return ans
            