class Solution:
    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        l, curr, ans = 0, 0, len(nums)+1
        for r in range(len(nums)):
            curr+=nums[r]
            while curr>=target:
                ans = min(ans, r-l+1)
                curr-=nums[l]
                l+=1
        return 0 if ans > len(nums) else ans