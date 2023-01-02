class Solution:
    def longestNiceSubarray(self, nums: List[int]) -> int:
        l = 0
        sub = 0
        ans = 0
        for r in range(len(nums)):
            num = nums[r]
            while (sub & num) != 0:
                sub = sub ^ nums[l]
                l+=1
            
            sub = sub | num
            ans = max(ans, r-l+1)
        return ans