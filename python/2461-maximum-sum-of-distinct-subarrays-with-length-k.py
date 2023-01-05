class Solution:
    def maximumSubarraySum(self, nums: List[int], k: int) -> int:
        n = len(nums)
        sumArray = [0] * (n); 

        for i in range(k) :
            sumArray[0] += nums[i]
        
        for i in range(k, n):   
            sumArray[i-k+1] = sumArray[i-k] + nums[i] - nums[i-k]
        
        ans, l, dict = 0, 0, {}
                
        for r in range(n):            
            if nums[r] in dict:
                l = dict[nums[r]] + 1
            
            dict[nums[r]] = r
            
            if r+1-l==k:
                ans += sumArray[l]
                l += 1
        return ans
            
            
                 
            
        