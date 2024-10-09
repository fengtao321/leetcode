class Solution:
    def findRepeatedDnaSequences(self, s: str) -> List[str]:
        if len(s)<10:
            return []
        
        hashset = set()
        ans = set()
        
        skey = []
        for r in range(10):
            skey.append(s[r])
        hashset.add(''.join(skey))

        for r in range(10, len(s)):
            skey.pop(0)
            skey.append(s[r])
            
            temp = ''.join(skey)
            if temp in hashset:
                ans.add(temp)
            else:
                hashset.add(temp)
        
        return list(ans)