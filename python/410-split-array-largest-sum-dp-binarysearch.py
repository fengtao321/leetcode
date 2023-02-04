class Solution:
    def splitArray(self, nums: List[int], k: int) -> int:
        def canSplit(max_num):
            curr = 0
            total_sub = 0
            for num in nums:
               curr += num
               if curr>max_num:
                    total_sub+=1
                    curr=num
            return total_sub<k
        
        l,r = max(nums), sum(nums)
        while l<= r:
            mid = (l+r)//2
            if canSplit(mid):
                r = mid-1
            else:
                l = mid + 1
        return l
                
            