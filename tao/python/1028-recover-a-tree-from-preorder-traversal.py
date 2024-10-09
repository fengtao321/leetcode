# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def recoverFromPreorder(self, traversal: str) -> Optional[TreeNode]:
        N, i, height = len(traversal), 0, 0
        
        def getTreeNode():
            nonlocal i
            c=traversal[i]
            while i+1 < N and traversal[i+1] != '-':
                c += traversal[i+1]
                i=i+1
            i+=1
            return TreeNode(int(c))
        
        heap = [getTreeNode()]
        while i<N:
            c = traversal[i]
            if c=='-': 
                height+=1
                i+=1
                continue
              
            while len(heap)>height:
                heap.pop(-1)
            
            parent, child = heap[-1], getTreeNode()
            if parent.left:
                parent.right = child
            else:
                parent.left = child
            
            heap.append(child)
            height=0
            
        return heap[0]
        
        