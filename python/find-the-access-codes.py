from functools import lru_cache
from math import comb

def solution(l):
    @lru_cache(None)
    def dfs(num, i, j):
        if i>=0 and l[i] % num !=0: return 0
        if j == 2: return 1
            
        res = 0
        pre = l[i] if i>=0 else 1
        for nxt in range(i+1, len(l)):
            res += dfs(pre, nxt, j+1)
        return res

    return dfs(1, -1, -1)
    

def main():
    tests = [[1,1,1], [1,1,1,1 ], [1,2,3,4,5,6],  [2,5,7], [1,1,1,2]]
    ans = [1, 4, 3,  0, 4]
    
    N = len(tests)
    isSuccess = True
    for i in range(N):
        res = solution(tests[i])

        if res!=ans[i]:
            print(tests[i], ans[i], res)
            isSuccess=False
    
    if isSuccess: print("Success!")
            
    

if __name__ == "__main__":
    main()