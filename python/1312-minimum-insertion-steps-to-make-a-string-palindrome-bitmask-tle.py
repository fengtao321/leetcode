class Solution:
    def minInsertions(self, s: str) -> int:
        N, ans = len(s), 0
        
        for i in range (1, 1<<(N-2)):
            substr = ""
            for j in range(N):
                if ((1<<j) & i) > 0:
                    substr+=s[j]
            if substr == substr[::-1]:
                ans = max(ans, N - len(substr))
                
        return ans
            