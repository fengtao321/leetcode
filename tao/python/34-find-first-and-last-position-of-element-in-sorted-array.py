class Solution:
    def searchRange(self, nums: List[int], target: int) -> List[int]:
        min_index = max_index = -1
        def dfs(l, r):
            if l>r:
                return
            
            nonlocal min_index, max_index
            m = (l+r)//2
            if nums[m] ==target:
                max_index = max(m, max_index)
                min_index = m if min_index<0 else min(min_index, m)
                dfs(l, m-1)
                dfs(m+1, r)
                return
            
            if nums[m] > target:
                return dfs(l, m-1)
            else:
                return dfs(m+1, r)
            
        dfs(0, len(nums)-1)
        return [min_index, max_index]
            
            