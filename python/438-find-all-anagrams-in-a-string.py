class Solution:
    def findAnagrams(self, s: str, p: str) -> List[int]:
        target = defaultdict(int)
        for c in p:
            target[c] += 1
        
        ans = []
        conditions = len(target)
        left = 0
        hash_map = defaultdict(int) 
        
        for i in range(len(s)):
            c = s[i]
            if c not in target:
                hash_map.clear()
                left = i+1
                conditions=len(target)
                continue
            
            if i - left + 1 > len(p):
                cl = s[left]
                if hash_map[cl] == target[cl]:
                    conditions+=1
                hash_map[cl] -=1
                left += 1
            
            hash_map[c] +=1
            if hash_map[c] == target[c]:
                    conditions-=1
                    if conditions == 0:
                        ans.append(left)
        return ans
            
            
                
                
            
            
            
            
        