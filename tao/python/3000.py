from collections import deque
from functools import lru_cache
from itertools import accumulate

class Solution:
    def getMaximumPoints(self, days, k: int) -> int:
        def calculateLeftPoints(start, end):
            if start< 0:
                return calculateLeftPoints(start + total, total) +  calculateLeftPoints(0, end)
            

        pre_points, pre_days, curr_point, curr_day = [], [], 0, 0
        for i, day in enumerate(days):
            curr_point += (1+day) * day //2
            curr_day+=day
            pre_points.append(curr_point)
            pre_days.append(curr_day)
            
        total = sum(days)
        
        q = deque([]) # [point, index]
        ans = current_point = 0
        
        for i, day in enumerate(days):
            j = -1
            while q and i - q[0][1] + 1>= k:
                p, j = q.popleft()
            
            if j>=0:
                current_point = pre_points[i]-pre_points[j]

            current_point += calculateLeftPoints(j,  k - curr_day[i] + curr_day[j])
            q.append([current_point, i])
            ans = max(ans, current_point)
        return ans

    
c = Solution()
print(c.getMaximumPoints([2,3,2], 4))
# print(c.getMaximumPoints("abcdef", 2))
# print(c.getMaximumPoints("aabbaa", 3))
# assert c.minimumChanges("abcac", 2) == 1
# assert c.minimumChanges("abcdef", 2) == 2
# assert c.minimumChanges("aabbaa", 3) == 0