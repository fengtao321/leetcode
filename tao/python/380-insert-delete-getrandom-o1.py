class RandomizedSet:

    def __init__(self):
        self.hashset = set()

    def insert(self, val: int) -> bool:
        if val in self.hashset:
            return False
        self.hashset.add(val)
        return True

    def remove(self, val: int) -> bool:
        if val in self.hashset:
            self.hashset.remove(val)
            return True
        return False
        

    def getRandom(self) -> int:
        num = random.randint(0, len(self.hashset))
        return list(self.hashset)[num]


# Your RandomizedSet object will be instantiated and called as such:
# obj = RandomizedSet()
# param_1 = obj.insert(val)
# param_2 = obj.remove(val)
# param_3 = obj.getRandom()