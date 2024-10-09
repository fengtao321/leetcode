class Solution:
    def longestCommonPrefix(self, strs: List[str]) -> str:
        strs.sort(key = lambda x: len(x))
       
        def rollingHash(i, str,hash_value):
            p, m = 31, 10**9 + 7
            return (hash_value + (1 + ord(str[i]) - ord('a')) * p) % m
               
        ans = hash_so_far = 0
        minStr = strs[0]
        while ans<len(minStr):
            hash_next_value =  rollingHash(ans, minStr, hash_so_far)
            for i in range(1, len(strs)):
                if hash_next_value!= rollingHash(ans, strs[i], hash_so_far):
                    return minStr[0:ans]
            ans += 1
            hash_so_far =hash_next_value
        
        return minStr[0:ans]