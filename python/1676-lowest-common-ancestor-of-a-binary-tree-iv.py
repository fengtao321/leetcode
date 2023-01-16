class Solution:
    def lowestCommonAncestor(self, root: 'TreeNode', nodes: 'List[TreeNode]') -> 'TreeNode':
        def dfs(node):
            if not node:
                return None
            if node in nodes:
                return node
            lNode, rNode = dfs(node.left), dfs(node.right)
            if lNode and rNode:
                return node
            if not lNode and not rNode:
                return None
            if lNode:
                return lNode
            if rNode:
                return rNode
        return dfs(root)
        
            