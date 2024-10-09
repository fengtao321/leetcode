class Solution:
    def strStr(self, haystack: str, needle: str) -> int:
        n = len(needle)
        if n > len(haystack):
            return -1
        
        hash_map, target = defaultdict(int),defaultdict(int)
        for c in needle:
            target[c] += 1
        
        conditions = len(target)
        
        l = 0
        for i in range(len(haystack)):
            c = haystack[i]
            hash_map[c] += 1
            if hash_map[c] == target[c]:
                conditions -= 1

            if i>=n:
                c = haystack[l]
                if hash_map[c] == target[c]:
                    conditions += 1
                hash_map[c] -= 1
                l+=1
                
            if conditions == 0 and haystack[l: i+1]==needle:
                return l
        return -1
        
            