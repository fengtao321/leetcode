# The idea is from Hao :) he is smart
class Solution:
    def minMoves(self, nums: List[int], limit: int) -> int:
        l = [0] * (2 + limit*2)
        N = len(nums)
        
        for i in range(N//2):
            min_num = min(nums[i], nums[N-1-i]) + 1
            max_num = max(nums[i], nums[N-1-i]) + limit
            total = nums[i] + nums[N-1-i]
            
            if min_num < total:
                if min_num>2: 
                    l[2]+=2
                    l[min_num] -=1
                else:
                    l[2]+=1
                l[total]-=1
            l[total+1]+=1
            l[max_num+1]+=1
        
        ans = float("infinity")
        curr = 0
        for i in range(2, len(l)):
            curr+=l[i]
            ans = min(ans, curr)
        
        return ans

            
            

        