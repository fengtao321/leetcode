class Solution:
    def checkInclusion(self, s1: str, s2: str) -> bool:
        def add_character(c):
            s2_record[c]+=1
            return -1 if c in s1_record and s2_record[c]<=s1_record[c] else 1
        
        def remove_character(c):
            s2_record[c]-=1
            return 1 if c in s1_record and s2_record[c]+1<=s1_record[c] else -1
        
        
        if len(s1)>len(s2): return False    
        s1_record = Counter(s1)
        s2_record = defaultdict(int)
        
        
        diff = N = len(s1)
        
        for i in range(N):
            diff += add_character(s2[i])
        
        if diff == 0: return True
        for i in range(N,len(s2)):
            diff += add_character(s2[i]) + remove_character(s2[i-N])
            if diff == 0: return True
        return False
        
            
        
        