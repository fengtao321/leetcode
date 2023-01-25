class Solution:
    def pushDominoes(self, dominoes: str) -> str:
        dominoes = 'L'+dominoes+'R'
        n = len(dominoes)
        l, ans = 0, list(dominoes)

        for r in range(1, n):
            cr = dominoes[r]
            if cr == '.':
                continue
            
            cl = dominoes[l]
            if cr == cl:
                while l < r:
                    ans[l] = cl
                    l+=1
                    
                    continue
            
            if cr == 'R' and cl == 'L':
                l = r
                continue
            
            if cr == 'L' and cl == 'R':
                mid = (l+r)//2
                l_end = mid -1 if (l+r)% 2==0 else mid
                
                while l <= l_end:
                    ans[l] = 'R'
                    l+=1
                    
                l = mid + 1
                while l <r:
                    ans[l] = 'L'
                    l+=1
                
        
        ans.pop(0)
        ans.pop(-1)
        return ''.join(ans)
            

            
        
