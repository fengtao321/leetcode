def sumXor(x):
    if x < 0: return 0
    r = x%4
    if r==0: return x
    if r==1: return 1
    if r==2: return x+1
    return 0

def solution(start, length):
    ans, first = 0, start - 1
    for i in range(length):
        last = first + length - i
        ans^=sumXor(first) ^ sumXor(last)
        first = last + i
    return ans
    

def main():
    assert solution(17,4) == 14
    assert solution(0,3) == 2            

if __name__ == "__main__":
    main()