class Solution:
    def maxFrequency(self, nums: List[int], k: int) -> int:
        if len(nums) == 1:
            return 1
        nums.sort()
        l = ans = counter = 0
        for r in range(1, len(nums)):
            counter += (nums[r] - nums[r-1]) * (r-l)
            while counter > k:
                counter -=  nums[r] - nums[l]
                l+=1
            
            ans = max(ans, (r-l+1))
        return ans
                