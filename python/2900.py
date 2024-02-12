from functools import lru_cache


class Solution:
    def getWordsInLongestSubsequence(self, n, words, groups):
        @lru_cache(None)
        def dfs(index, pre):
            if index==n: return []
            if groups[index] == pre:
                return dfs(index+1, pre)
            
            return [words[index]] + dfs(index+1, groups[index])
        
        start1 = dfs(0, 1)
        start0 = dfs(0, 0)        
        return start1 if len(start1) > len(start0) else start0


c = Solution()
assert len(c.getWordsInLongestSubsequence(3,["e","a","b"],[0,0,1])) == 2
assert len(c.getWordsInLongestSubsequence(4,["a","b","c","d"],[1,0,1,1])) == 3
