import datetime
import time

class Solution:
    def findMinDifference(self, timePoints: List[str]) -> int:
        def convert(time_str):
            l = list(time_str.split(":"))
            return int(l[0]) * 60 + int(l[1])
            
        for i, time in enumerate(timePoints):
            timePoints[i] = convert(time)
            
        timePoints.sort()
        ans = timePoints[0] + 24*60 - timePoints[-1]
        
        for i in range(1, len(timePoints)):
            ans = min(ans, timePoints[i] - timePoints[i-1])
        
        return ans