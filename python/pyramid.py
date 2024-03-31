from collections import deque
from functools import lru_cache
from itertools import accumulate

class Solution:
    def decode(self, message_file):
        message_list = []
        added = set()
        with open(message_file) as file:
            while line := file.readline():
                l = line.rstrip().split(" ")
                if l[0] not in added:
                    message_list.append(l)
                    added.add(l[0])
        message_list.sort(key = lambda m: int(m[0]))
        
        ans, index, pyramid_counter, N = [], 0, 1, len(message_list)
        while index<N:
            ans.append(message_list[index][1])
            pyramid_counter+=1
            index+=pyramid_counter
        print(" ".join(ans))
        return " ".join(ans)
        

    

    
c = Solution()
c.decode("coding_qual_input.txt")
# print(c.getMaximumPoints([2,3,2], 4))
# print(c.getMaximumPoints("abcdef", 2))
# print(c.getMaximumPoints("aabbaa", 3))
# assert c.minimumChanges("abcac", 2) == 1
# assert c.minimumChanges("abcdef", 2) == 2
# assert c.minimumChanges("aabbaa", 3) == 0