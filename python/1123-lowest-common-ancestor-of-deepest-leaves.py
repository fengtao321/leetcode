class Solution:
    def lcaDeepestLeaves(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        def dfs(root):
            if not root:
                return None, 0
            
            lNode, lDepth = dfs(root.left)
            rNode, rDepth = dfs(root.right)
            
            if lDepth == rDepth:
                return root, lDepth+1
            
            if lDepth > rDepth:
                return lNode, lDepth + 1
            
            if rDepth > lDepth:
                return rNode, rDepth + 1
            
        return dfs(root)[0]