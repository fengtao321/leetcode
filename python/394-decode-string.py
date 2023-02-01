class Solution:
    def decodeString(self, s: str) -> str:
        stack = []
        temp = ""
        for c in s:
            if c== "[":
                stack.append(temp)
                temp = ""
            elif c.isnumeric() and temp.isalpha():
                stack.append(temp)
                temp=c
            elif c=="]":
                last = stack.pop()
                while last.isalpha():
                    temp = last + temp
                    last = stack.pop()
                    
                stack.append(temp*int(last))
                temp = ""   
            else:
                temp +=c
                
        return "".join(stack) + temp
        