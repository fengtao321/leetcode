class Solution:
    def removeDuplicates(self, s: str, k: int) -> str:
        stack, ans = [], ""
        for c in s:
            counter = stack.pop()[1] + 1 if stack and stack[-1][0] == c else 1
            counter = counter % k
            if counter > 0:
                stack.append((c, counter))
        for t in stack:
            ans += t[0]*t[1]
        
        return ans
                    
                
                
            
            