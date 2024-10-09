class Solution:
    def alienOrder(self, words: List[str]) -> str:
        alienMap = {c: set() for word in words for c in word}
        # alienMap = defaultdict(set)
        prerequisite =  {c: 0 for word in words for c in word}
        last = ""
        for word in words:
            for j in range(max(len(word), len(last))):
                if j == len(last):
                    break
                if j == len(word):
                    break
                if last[j] != word[j]:
                    if word[j] not in alienMap[last[j]]:
                        alienMap[last[j]].add(word[j])
                        prerequisite[word[j]] += 1
                    break       
            last = word
        queue = ""
        for c in prerequisite:
            if prerequisite[c] == 0:
                queue = queue + c
        index = 0

        while index < len(queue) and alienMap:
            if queue[index] in alienMap:
                for c in alienMap[queue[index]]:
                    prerequisite[c] -= 1
                    if prerequisite[c] == 0:
                        queue = queue + c
                del alienMap[queue[index]]
            index +=1

        return queue if len(queue) == len(prerequisite) else ""
        
                
        
        
        
        
        
        
                
                
                