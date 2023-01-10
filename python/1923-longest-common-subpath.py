class Solution:
    def longestCommonSubpath(self, n: int, paths: List[List[int]]) -> int:
        path = paths[0]
        currDic = defaultdict(list)
        
        for i in range(len(path)):
            currDic[[path[i]]].add(i)
        
        
        
            