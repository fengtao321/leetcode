def solution(s):
    if not s: return 0
    
    start_char, res, l = s[0], 1, []    
    for i, c in enumerate(s):
        if c == start_char: l.append(i) 
    
    l_length, s_length = len(l), len(s)
    if l_length == 1: return res
    
    pattern_length = nxt = l[1]
    
    for i, start in enumerate(l):
        if start<nxt: continue
        
        end = start + pattern_length
        if (end == s_length or (end < s_length and s[end] == start_char)) and s[start: end] == s[:pattern_length]:
            res+=1
            nxt = end  
        else:
            res = 1
            pattern_length = l[i+1] if i+1<l_length else s_length
    return res

def main():
    tests = [ "abccbaabccba", "abcabcabcabc", "", "abccbaabccbaa"]
    ans = [2, 4, 0, 1]
    
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