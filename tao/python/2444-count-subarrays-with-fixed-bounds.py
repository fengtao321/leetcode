class Solution:
    def countSubarrays(self, nums: List[int], minK: int, maxK: int) -> int:
        ans,l,  minI, maxI = 0, -1, -1, -1

        for r, num in enumerate(nums):
 
            if num >maxK or num < minK:
                l = r
                minI = maxI = -1
                continue
            
            if num==maxK:
                maxI = r

            if num==minK:
                minI = r
                
            if maxI>=0 and minI>=0:
                ans += min(minI, maxI) - l 

        return ans


