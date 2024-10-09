class Solution:
    def makeSimilar(self, nums: List[int], target: List[int]) -> int:    
        nums_odd = [num for num in nums if num&1] 
        nums_even = [num for num in nums if not num&1]
        target_odd = [num for num in target if num&1] 
        target_even = [num for num in target if not num&1] 
        
        nums_odd.sort()
        nums_even.sort()
        target_odd.sort()
        target_even.sort()
        
        if len(nums_odd)!=len(target_odd) or len(nums_even)!=len(target_even): return 0
        ans_i, ans_j = 0, 0
        
        for i in range(len(nums_odd)):
            if nums_odd[i] > target_odd[i]: ans_i += (nums_odd[i]-target_odd[i])/2
            if nums_odd[i] < target_odd[i]: ans_j += (nums_odd[i]-target_odd[i])/2

        for i in range(len(nums_even)):
            if nums_even[i] > target_even[i]: ans_i += (nums_even[i]-target_even[i])/2
            if nums_even[i] < target_even[i]: ans_j += (nums_even[i]-target_even[i])/2
        
        return int(ans_i) if ans_i==-ans_j else 0