class Solution:
    def maximumRemovals(self, s: str, p: str, removable: List[int]) -> int:                    
        def isSubsequence(hash_set):
            i = j = 0
            while i < len(s) and j < len(p):
                if i in hash_set:
                    i+=1
                    continue
                
                if s[i]==p[j]:
                    j+=1
                i+=1
            return True if j==len(p) else False
        
        l, r = 0, len(removable) -1
        while l<=r:
            mid = (r+l)//2
            hash_set = set(removable[: mid+1])
            if isSubsequence(hash_set):
                l = mid+1
            else:
                r = mid -1      
        return l     
                