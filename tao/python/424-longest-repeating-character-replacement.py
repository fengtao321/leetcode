from collections import defaultdict


class Solution:
    def characterReplacement(self, s: str, k: int) -> int:
        def charToNum(index):
            return ord(s[index]) - ord('a')
        
        l, ans, arr = 0, 0, [0] * 26           
        for r in range(len(s)):
            arr[charToNum(r)] +=1
            while r -l -max(arr) > k:
                arr[charToNum(l)] -=1
                l+=1
        
            ans = max(ans, r-l)
        return ans
        
        
        