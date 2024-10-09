class Solution:
    def minFlips(self, s: str) -> int:
        N = len(s)
        l, counter_zero, counter_one, ans, sList = 0, 0, 0, N, [int(i) for i in s]        

        for r in range(2*N):
            counter_zero +=1 if r&1!=sList[r%N] else 0
            counter_one +=1 if r&1==sList[r%N] else 0
            
            if r >= N:
                counter_zero -=1 if l&1!=sList[l] else 0
                counter_one -=1 if l&1==sList[l] else 0
                ans = min(ans, counter_zero, counter_one)
                l+=1
                
        return ans