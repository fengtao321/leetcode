class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        intervals.sort()
        ans = []
        ans.append(intervals[0])
        for interval in intervals:
            last = ans[len(ans)-1]
            
            if last[1]<interval[0]:
                ans.append(interval)
                continue
            
            if interval[1] > last[1]:
                ans[len(ans)-1][1] = interval[1]
        
        return ans
            
            
            