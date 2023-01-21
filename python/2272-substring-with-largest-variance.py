class Solution:
    def largestVariance(self, s: str) -> int:
        ans = 0
        hashset = set(s)
        for x in range(ord('a'), ord('z')+1):
            xchar = chr(x)
            if xchar not in hashset:
                continue
                
            for y in range(ord('a'), ord('z')+1): 
                ychar = chr(y)
                if x==y or ychar not in hashset:
                    continue
                    
                r_res = l_res = l_min = l = 0
                hasMin = False
                for r in range(len(s)):
                    if s[r] == xchar:
                        r_res +=1
                     
                    if s[r] == ychar:
                        r_res -=1
                        hasMin = True
                        
                        
                        while l < r:
                            if s[l] == xchar:
                                l_res +=1
                            if s[l] == ychar:
                                l_res -=1
                            l+=1
                            l_min = min(l_res, l_min)

                           
                    if r_res > 0 and hasMin:
                        ans = max(ans, (r_res - l_min))
               
        return ans
                    
                
                