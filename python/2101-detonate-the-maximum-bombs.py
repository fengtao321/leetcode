class Solution:
    def maximumDetonation(self, bombs: List[List[int]]) -> int:
        mp = {c: [] for c in range(len(bombs))}
        for i  in range(len(bombs)):
             for j  in range(i,len(bombs)):
                 dist = pow((bombs[j][1] -  bombs[i][1]),2) +  pow((bombs[j][0] -  bombs[i][0]),2) 
                 if dist <= pow(bombs[i][2], 2):
                     mp[i].append(j)
                 if dist <= pow(bombs[j][2], 2):
                     mp[j].append(i)
        
        def dfs(bomb, visited):
            if bomb in visited:
                return 0
            
            visited.add(bomb)
            num = 0
            
            for n in mp[bomb]:
                num += dfs(n, visited)
            return num+1
            
            
            
        ans = 0
        for i in range(len(bombs)):
            ans = max(ans, dfs(i, set()))
        return ans