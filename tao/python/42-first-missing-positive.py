class Solution:
    def firstMissingPositive(self, nums: List[int]) -> int:
        N = len(nums)
        for i in range(N):
            if nums[i]<=0:
                nums[i] = N+1

        for i in range(N):
            if abs(nums[i]) > N: continue
    
            j = abs(nums[i])-1
            nums[j] = -abs(nums[j])

        for i in range(N):
            if nums[i]>=0:
                return i+1
        
        return N+1