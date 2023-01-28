class Solution:
    def checkPossibility(self, nums: List[int]) -> bool:        
        is_changed = True
        for i in range(len(nums)-1):
            if nums[i]<=nums[i+1]:
                continue
            if not is_changed:
                return False
            
            if i==0 or nums[i+1]>=nums[i-1]: #[32]4567 or 1[32]4567
                nums[i] = nums[i-1]
            else:  #4,[7,3]
                 nums[i+1] = nums[i]
            is_changed = False
                
        return True