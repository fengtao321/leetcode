def solution(x,y):
    first = 1 + y*(y-1)//2
    ans = first + y*(x-1) + x*(x-1) //2
    return ans
    

def main():
    tests = [[5,10], [3,2], [2,5], [2,3]]
    ans = [96, 9, 17, 8]
    
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