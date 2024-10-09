class Solution:
    def orderlyQueue(self, s: str, k: int) -> str:
        if k>1:
            return ''.join(sorted(s))

        ans = s
        for i in range(len(s)-1):
            s = s[1:] + s[0]
            ans = min(ans, s)
        return ans