# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def getDirections(self, root: Optional[TreeNode], startValue: int, destValue: int) -> str:
        self.s = self.d = []
        
        def getPath(node, arr):

            if not node or (self.s and self.d):
                return
            
            if node.val == startValue:
                self.s = arr.copy()
                          
            if node.val == destValue:
                self.d = arr.copy()
            
            arr.append('L')
            getPath(node.left, arr)
            arr.pop(-1)
            arr.append('R')
            getPath(node.right, arr)
            arr.pop(-1)
        
        getPath(root, [])
        ans = ""
        i = 0
        while i < len(self.s) and i < len(self.d) and self.s[i]==self.d[i]:
            i+=1
              
        for _ in range(i, len(self.s)):
            ans +='U'
        for j in range(i, len(self.d)):
            ans += self.d[j]   
            
        return ans 
        
        