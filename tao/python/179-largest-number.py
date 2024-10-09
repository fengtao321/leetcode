class Solution:
    def largestNumber(self, nums: List[int]) -> str:
        ans = []
        def isLessThan(x, y):
            i = len(x)
            j = len(y)
            
            while i>1 or j>1:
                if int(x[i])>int(y[j]):
                    return False
                if i > 1:
                    i -= 1
                if j > 1:
                    j -= 1
                    
            return True   
           
        
        for num in nums:
            i = 0
            while isLessThan(str(num), str(ans[i])):
                i+=1
            ans.insert(i, num)
        
        return ''.join(ans)
            