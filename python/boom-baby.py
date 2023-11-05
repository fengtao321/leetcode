def dfs(x, y):
    if y>x: x,y = y, x
    if y==1: return x-1
    
    d, m = divmod(x, y)
    if m==0: return -1
    cur = dfs(m,y)
    return cur if cur<0 else d+cur
    
def solution(x,y):
    ans = dfs(int(x), int(y))
    return "impossible" if ans<0 else str(ans)
    

def main():
    tests = [['4','7'], ['2','1'], ['4','2']]
    ans = ["4", "1", "impossible"]
    
    N = len(tests)
    isSuccess = True
    for i in range(N):
        res = solution(tests[i][0], tests[i][1])

        if res!=ans[i]:
            print(tests[i], ans[i], res)
            isSuccess=False
    
    if isSuccess: print("Success!")
            
    

if __name__ == "__main__":
    main()