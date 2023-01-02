class Solution:
    def numDistinct(self, s: str, t: str) -> int:
        if len(t)>len(s): 
            return 0
        
        matrix = [[0 for j in range(len(s) + 1)] for i in range(len(t) + 1)]
        
        for j in range(0, len(s)+1, 1):
            matrix[len(t)][j] = 1;
            
        for i in range(len(t)-1, -1, -1):
            for j in range(len(s)-1, -1, -1):
                if s[j] == t[i]:
                    matrix[i][j] = matrix[i+1][j+1] +matrix[i][j+1]
                else:
                    matrix[i][j] = matrix[i][j+1]

                
        return matrix[0][0]