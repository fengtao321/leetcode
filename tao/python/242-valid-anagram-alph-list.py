class Solution:
    def isAnagram(self, s: str, t: str) -> bool:
        if len(s)!=len(t):
            return False
        
        record = [0] * 26
        for c in s:
            record[ord(c)-97] +=1
        
        for c in t:
            key = ord(c)-97
            if record[key] < 1:
                return False
            record[key] -=1
        
        return True