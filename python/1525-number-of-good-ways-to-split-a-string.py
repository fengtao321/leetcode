class Solution:
    def numSplits(self, s: str) -> int:
        right = {c: 0 for c in s}
        left = {}
        ans = 0
        for c in s:
            right[c] += 1
        for c in s:
            if right[c] == 1:
                right.pop(c)
            else:
                right[c] =  right[c] - 1
            
            left[c] = left.get(c, 0) + 1   
            if len(right) == len(left):
                ans+=1
        return ans
            