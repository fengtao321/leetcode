class Solution:
    def findTheLongestSubstring(self, s: str) -> int:
        l = list(s)
        vowels = {'a': 1, 'e': 2,'i': 3,'o': 4,'u': 5}
        
        record = ans = 0
        hash_map = {record:-1}

        for i, c in enumerate(l):
            if c in vowels: 
                record ^= (1<< vowels[c])
            
            if record==0: ans = max(ans, i+1)
            else:   
                if record not in hash_map:
                    hash_map[record] = i
                else:
                    ans = max(ans, i-hash_map[record]) 
        return ans
                