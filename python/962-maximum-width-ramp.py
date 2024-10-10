class Solution:
    def maxWidthRamp(self, nums: List[int]) -> int:
        l = []
        for i, num in enumerate(nums):
            l.append([num, i])
        
        l.sort()
        min_index = []

        min_index = l[0][1]
        ans = 0

        for [num, i] in l:
            ans = max(i-min_index, ans)
            min_index = min(min_index, i)
            
        
        return ans
        
