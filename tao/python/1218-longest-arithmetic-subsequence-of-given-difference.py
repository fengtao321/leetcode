class Solution:
    def longestSubsequence(self, arr: List[int], difference: int) -> int:
        dp = {}
        ans = 0
        for num in arr:
            dp[num] = dp.get(num-difference, 0) + 1
            ans = max(ans, dp[num])
        return ans
        