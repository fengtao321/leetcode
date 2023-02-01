class Solution:
    def removeKdigits(self, num: str, k: int) -> str:
        stack, n = [], len(num)
        if n == k:
            return "0"

        for x in num:
            while k> 0 and stack and int(stack[-1]) > int(x):
                stack.pop()
                k-=1
            if stack or x!='0': 
                stack.append(x)
                
        while k> 0 and stack:
            stack.pop()
            k-=1

        return "".join(stack) if stack else "0"
        
        