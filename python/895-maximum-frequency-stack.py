


from collections import deque


class FreqStack:

    def __init__(self):
        self.frequency_map = {}
        self.hash_map = defaultdict(int)
        self.max_frequency = 0
        

    def push(self, val: int) -> None:
        freq = self.hash_map[val] + 1
        self.hash_map[val] = freq
        
        if freq in self.frequency_map:
            self.frequency_map[freq].append(val)
        else:
            self.frequency_map[freq] = deque([val])
            self.max_frequency = max( self.max_frequency , freq)
        
        
    def pop(self) -> int:
        q = self.frequency_map[self.max_frequency]
        val = q.popleft()
        self.hash_map[val] = self.hash_map[val] - 1
        if not q:
           del self.frequency_map[self.max_frequency]
           self.max_frequency -= 1
        return val
       


# Your FreqStack object will be instantiated and called as such:
# obj = FreqStack()
# obj.push(val)
# param_2 = obj.pop()