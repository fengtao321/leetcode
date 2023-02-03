"""
# Definition for a Node.
class Node:
    def __init__(self, val: int = 0, left: 'Node' = None, right: 'Node' = None, next: 'Node' = None):
        self.val = val
        self.left = left
        self.right = right
        self.next = next
"""

class Solution:
    def connect(self, root: 'Optional[Node]') -> 'Optional[Node]':
        if not root: return root
    
        q = deque([root])
        while q:
            nex = None
            size = len(q)
            for _ in range(size):
                curr = q.pop()
                curr.next = nex
                nex = curr
                if curr.right:
                    q.appendleft(curr.right)
                if curr.left:
                    q.appendleft(curr.left)
        return root