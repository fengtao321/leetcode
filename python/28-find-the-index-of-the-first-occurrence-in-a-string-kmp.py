class Solution:
    def strStr(self, haystack: str, needle: str) -> int:
        n = len(needle)
        kmp_arr = [0] * n
        curr,prev = 1, 0
        while i < n:
            if needle[curr] == needle[prev]:
                kmp_arr[curr] = prev + 1
                curr += 1
                prev +=1
            elif prev == 0:
                curr += 1
            else:
                prev = kmp_arr[prev - 1] # not prev -= 1, think about adcadde
        
        i, j = 0, 0
        while i < (len(haystack)):
            if needle[j] == haystack[i]:
                j+=1
                i+=1
            elif j==0:
                i+=1
            else:
                j = kmp_arr[j-1]
                
            if j == n:
                return i-n + 1
        return -1
                