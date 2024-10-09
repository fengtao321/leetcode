class MyStack:

    def __init__(self):
        self.q1 = deque([])
        self.q2 = deque([])

    def push(self, x: int) -> None:
        self.q1.append(x)
        while self.q2:
            self.q1.append(self.q2.popleft())
        self.q1, self.q2 = self.q2, self.q1

    def pop(self) -> int:
        return self.q2.popleft()

    def top(self) -> int:
        return self.q2[0]

    def empty(self) -> bool:
        return False if self.q2 else True


# Your MyStack object will be instantiated and called as such:
# obj = MyStack()
# obj.push(x)
# param_2 = obj.pop()
# param_3 = obj.top()
# param_4 = obj.empty()