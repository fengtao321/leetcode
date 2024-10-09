class Solution:
    def maxScoreWords(self, words: List[str], letters: List[str], scores: List[int]) -> int:
        counter, N = [0] * 26, len(words)
        for c in letters:
            counter[ord(c) - ord('a')] += 1
        ans = 0
        for i in range(1<<N):   #2**N
            temp = counter.copy()
            score, isFormed = 0, True
            for j in range(N):
                if (i & 1<<j)==0: continue
                for c in words[j]:                    
                    key = ord(c) - ord('a')
                    temp[key] -= 1
                    if temp[key]<0:
                        isFormed = False
                        break
                    score +=scores[key]
                if not isFormed: break   
            if isFormed: ans = max(ans, score)
                
        return ans
                
            