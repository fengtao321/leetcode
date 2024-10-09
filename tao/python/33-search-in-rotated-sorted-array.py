class Solution:
    def search(self, nums: List[int], target: int) -> int:
        n = len(nums)
        l,r = 0, n-1
        while l<=r:
            m = (l+r)//2
            if nums[m] == target:
                return m
            if target<nums[m]: 
                if nums[m] >= nums[0] and target <= nums[n-1] and nums[0]>nums[n-1]:
                    l = m+1 
                else:
                    r = m-1
            else:
                if nums[0] <= target and nums[m]<=nums[n-1] and nums[0]>nums[n-1]:
                    r = m-1
                else:
                    l = m+1
        
        return -1