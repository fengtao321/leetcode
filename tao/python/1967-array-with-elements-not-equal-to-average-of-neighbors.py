class Solution:
    def rearrangeArray(self, nums: List[int]) -> List[int]:
        nums.sort()
        N = len(nums)
        ans, l, r = [0] * N, 0, N-1
      
        for i in range(0, N-1, 2):
            ans[i], ans[i+1] = nums[l],nums[r]
            l+=1
            r-=1
            
        if N&1 ==1:
             ans[N-1] = nums[l]
        return ans