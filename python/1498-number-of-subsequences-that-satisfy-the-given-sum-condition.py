class Solution:
    def numSubseq(self, nums: List[int], target: int) -> int:
        nums.sort()
        ans, MOD, r = 0, (10**9 + 7), len(nums)-1
        for l, left in enumerate(nums):
            while l<=r and (left+nums[r])>target:
                r-=1
            if r>=l:
                ans = (ans +(2**(r-l))) % MOD
        
        return ans
        
        