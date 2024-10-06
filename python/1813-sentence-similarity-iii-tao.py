class Solution:
    def areSentencesSimilar(self, sentence1: str, sentence2: str) -> bool:
        if sentence1 == sentence2: return True
        if len(sentence1) == len(sentence2): return False
        if len(sentence1) < len(sentence2): return  self.areSentencesSimilar(sentence2, sentence1)
        sentence1 = sentence1.split()
        sentence2 = sentence2.split()
        
        N, M = len(sentence1), len(sentence2)
        
        start_index, end_inedex = -1, M
        start_stop = end_stop = False
        for i in range(M):
            if start_stop and end_stop: break
            if not start_stop and start_index+1<end_inedex and sentence2[start_index+1]==sentence1[i]:
                start_index+=1
            else:
                start_stop = True
            
            if not end_stop and start_index+1<end_inedex and sentence2[end_inedex-1]==sentence1[N-1-i]:
                end_inedex-=1
            else:
                end_stop = True
            
        print(start_index, end_inedex)
        return True if start_index+1==end_inedex else False