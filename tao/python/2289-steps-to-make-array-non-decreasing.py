class Solution:
    def totalSteps(self, nums: List[int]) -> int:
        ans = 0
       
        maxNum = nums[0]
        monoQueue = [] #num, step
            
        for num in nums:
            # print(monoQueue)
            counter = 0
            while(monoQueue and num>=monoQueue[-1][0]):
                counter = max(counter,monoQueue[-1][1])
                monoQueue.pop(-1)
            
            if num>=maxNum:
                maxNum = num
                counter = 0
            else:
                counter += 1
                ans = max(ans, counter) 
                
            monoQueue.append([num, counter])
        return ans
    
    
# [5,3,4,4,7,3,6,11,8,5,11]
# [4,5,7,7,13]
# [7,14,4,14,13,2,6,13]
# [10,1,2,3,4,5,6,1,2,3]
# [5,14,15,2,11,5,13,15]
    
    
    
