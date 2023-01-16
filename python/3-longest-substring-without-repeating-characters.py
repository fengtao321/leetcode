class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        hashMap = {}
        ans = l = 0
        
        for r in range(len(s)):
            char = s[r]
            if char in hashMap and hashMap[char]>=l:
                l = hashMap[char] + 1

            hashMap[char] = r
            ans = max(ans, r-l+1)
        return ans
            
                