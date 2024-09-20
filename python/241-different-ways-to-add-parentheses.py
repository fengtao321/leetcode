class Solution:
    def diffWaysToCompute(self, expression: str) -> List[int]:
        signs = []
        nums = []
        
        num_txt = ""
        for c in expression:
            if not c.isnumeric():
                signs.append(c)
                nums.append(int(num_txt))
                num_txt = ""
            else:
                num_txt += c
        nums.append(int(num_txt))
        
        def dfs(i, j):
            if i==j: return [nums[i]]
            
            res = []
            for k in range(i, j):
                sign = signs[k]
                left = dfs(i, k)
                right = dfs(k+1, j)
                
                for x in left:
                    for y in right:
                        if sign == '+': res.append(x+y)
                        if sign == '-': res.append(x-y)
                        if sign == '*': res.append(x*y)
            
            return res
                
        return dfs(0, len(nums)-1)
        