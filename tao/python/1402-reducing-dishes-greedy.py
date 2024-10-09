class Solution:
    def maxSatisfaction(self, satisfaction: List[int]) -> int:
        satisfaction.sort(reverse=True)
        ans, prem = 0, 0
        for num in satisfaction:
            prem += num
            if prem<0:
                return ans
            ans +=prem

        return ans        