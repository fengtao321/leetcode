class StockSpanner:

    def __init__(self):
        self.stack = []   
        self.counter = 1

    def next(self, price: int) -> int:
        while self.stack and self.stack[-1][0] <= price:
            self.stack.pop()
        ans = self.counter - self.stack[-1][1] if self.stack else self.counter
        self.stack.append((price, self.counter))
        self.counter += 1
        return ans
        


# Your StockSpanner object will be instantiated and called as such:
# obj = StockSpanner()
# param_1 = obj.next(price)