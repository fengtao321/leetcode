class Solution:
    def lexicalOrder(self, n: int) -> List[int]:          
        ans = []
        def dfs(curr):
            if curr>n: return
            ans.append(curr)
            
            if curr*10<=n: dfs(curr*10)
            
            if curr%10<9: dfs(curr+1)
            
        dfs(1)   
        return ans
                