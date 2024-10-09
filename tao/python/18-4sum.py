class Solution:
    def fourSum(self, nums: List[int], target: int) -> List[List[int]]:
        N = len(nums)
        if N<4: 
            return []
        ans= set()
        nums.sort()
        for i in range(N-3):
            if i>0 and nums[i]==nums[i-1]:
                continue
            for j in range(i+1, N-2):
                if j>i+1 and nums[j]==nums[j-1]:
                    continue
                curr_target = target - nums[i] - nums[j]
                l,r = j+1, N-1
                while l<r:
                    two_sum =  nums[l] + nums[r]
                    if two_sum == curr_target:
                        ans.add((nums[i], nums[j], nums[l], nums[r]))
                        l+=1
                        r-=1
                        continue;
                    if two_sum<curr_target:
                        l+=1
                    else:
                        r-=1
        return list(ans)