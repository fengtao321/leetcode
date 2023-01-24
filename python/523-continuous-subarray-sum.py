class Solution:
    def checkSubarraySum(self, nums: List[int], k: int) -> bool:
        if len(nums)==1:
            return False
        
        hashmap = {}
        hashmap[0] = -1
        rem = 0
        for i in range(len(nums)):
            rem = (rem+nums[i])%k
            if rem not in hashmap:
                hashmap[rem] = i
                continue
                
            if hashmap[rem]<(i-1):
                return True
 
        return False