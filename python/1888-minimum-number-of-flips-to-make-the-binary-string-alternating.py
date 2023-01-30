class Solution:
    def minFlips(self, s: str) -> int:
        N = len(s)
        l, counter_zero, counter_one, start_zero, start_one, ans, sList = 0, 0, 0, [0]*2*N, [0]*2*N, N, [int(i) for i in s]*2
        start_zero[0], start_one[0] = 0, 1
        
        for i in range(1, 2*N):
            start_zero[i] = 1 if start_zero[i-1]==0 else 0
            start_one[i] = 1 if start_one[i-1]==0 else 0

        for r in range(2*N):
            counter_zero +=1 if start_zero[r]!= sList[r] else 0
            counter_one +=1 if start_one[r]!= sList[r] else 0
            
            if r==N-1:
                ans = min(ans, counter_zero, counter_one)

            if r >= N:
                counter_zero -=1 if start_zero[l]!= sList[l] else 0
                counter_one -=1 if start_one[l]!= sList[l] else 0
                ans = min(ans, counter_zero, counter_one)
                l+=1
                
                
        return ans
            
            
        
        
        
        