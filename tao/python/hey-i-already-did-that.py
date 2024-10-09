def minus(asc_num_list, b, k):
    res = [0] * k
    
    for i in range(k):
        res[k-1-i] = res[k-1-i] + asc_num_list[i] - asc_num_list[k-1-i]
        if res[k-1-i] < 0:
            res[k-i-2]-=1
            res[k-1-i] = b+res[k-1-i]
    return res

def getStrFromList(l):
    return ''.join(str(num) for num in l)

def solution(n,b):
    visited, k, index = {}, len(n), 1
    l = [int(c) for c in n]
    str_num = n
    while str_num not in visited:
        visited[str_num] = index
        index+=1
        l = minus(sorted(l), b, k)
        str_num = getStrFromList(l)
    return index -  visited[str_num]   

def main():
    tests = [['1211',10], ['210022',3]]
    ans = [1, 3]
    
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