class Solution:
    def maxTotalReward(self, rewardValues: List[int]) -> int:
        s = set(rewardValues)
        rewardValues = sorted(list(s))
        max_num = rewardValues.pop()
        
        s = set()
        
        for i, v in enumerate(rewardValues):
            nxt_s = {v}
            
            for num in s:
                if v+num<max_num and num<v: nxt_s.add(v+num)
            
            s=s.union(nxt_s)
        
        return max_num+max(s) if s else max_num
            
            
        
        