class Solution:
    def countPalindromicSubsequence(self, s: str) -> int:
        ans, hashmap = set(), defaultdict(list)
        
        for i in range(len(s)):
            hashmap[s[i]].append(i)
            
        for c in list(hashmap.keys()):
            if len(hashmap[c]) < 2:
                del hashmap[c]
            
        for i in range(1,len(s)):                
            for c in list(hashmap.keys()):
                arr = hashmap[c]
                if arr[-1]<=i:
                    del hashmap[c]
                    continue
                if arr[0] < i:
                    ans.add((s[i], c))

        return len(ans)
            
            