class Solution:
    def minAddToMakeValid(self, s: str) -> int:
        ans = 0
        
        counter = 0
        
        for c in list(s):
            if c == '(': counter+=1
            else:
                if counter == 0: 
                    ans+=1
                else:
                    counter-=1
        
        return ans+counter