class Solution:
    def sortColors(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        l, piv, r = 0, 0, len(nums)
        while piv < r:
            if nums[piv] == 1:
                piv +=1
            if nums[piv] < 1:
                nums[piv], nums[l] = nums[l], nums[piv]
                l +=1
            else:
                nums[piv], nums[r] = nums[r], nums[piv]
                r -=1
                
        
        