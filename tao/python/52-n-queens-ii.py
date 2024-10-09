class Solution:
    def totalNQueens(self, n: int) -> int:
        col, left_slash, right_slash = [0]*n, [0]*(2*n-1), [0]*(2*n-1)
        def dfs(i):
            if i==n:
                return 1
            curr_solution = 0
            for j in range(n):
                if col[j]>0 or left_slash[i+j]>0 or right_slash[i-j+n-1] >0:
                    continue
                col[j] =left_slash[i+j]=right_slash[i-j+n-1]=1
                curr_solution += dfs(i+1)
                col[j] =left_slash[i+j]=right_slash[i-j+n-1]=0
            return curr_solution

        return dfs(0)