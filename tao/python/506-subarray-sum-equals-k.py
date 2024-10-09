class Solution:
    def subarraySum(self, nums: List[int], k: int) -> int:
        prefix = defaultdict(int)
        prefix[0] = 1
        
        ans = 0
        curr = 0
        for num in nums:
            curr += num
            ans +=prefix[curr - k]
            prefix[curr] += 1
            
        return ans