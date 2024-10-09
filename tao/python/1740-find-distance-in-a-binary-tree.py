# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def findDistance(self, root: Optional[TreeNode], p: int, q: int) -> int:
        if p == q:
            return 0
        ans = 0
        def commonAncestor(node):
            if not node:
                return None
            if node.val == p or node.val == q:
                return node
            
            l, r = commonAncestor(node.left), commonAncestor(node.right)
                        
            if l and r:
                return node
            
            if l:
                return l
            
            if r:
                return r
            
            return None
        
        def dfs(node, val):
            if not node:
                return -1
            if node.val == val:
                return 0
            
            l, r = dfs(node.left, val), dfs(node.right, val)
            if l>=0 or r>=0:
                return 1+max(l,r)
            return -1
            
        
        ancestor = commonAncestor(root)
        return dfs(ancestor, p) + dfs(ancestor, q)
        