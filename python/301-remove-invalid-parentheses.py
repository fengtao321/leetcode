class Solution:
    def removeInvalidParentheses(self, s: str) -> List[str]:
        remove_right= left = right = 0
        for c in s:
            if c == ')' and left <= right:
                remove_right +=1
                
            if c == '(':
                left += 1
            if c == ')':
                right += 1
        
        remove_left = left - right
        
        ans = set()
        
        def dfs(i, l_counter, r_counter, l_remove, r_remove, str_arr):
            if i==len(s):
                 ans.add(''.join(str_arr))
                 return
                
            if l_counter<r_counter or (left - l_counter - l_remove) > (right - r_counter - r_remove):
                return
            
            c = s[i]
            if c!='(' and c!=')':
                return dfs(i+1, l_counter, r_counter, l_remove, r_remove, str_arr.append(c))
            
            if c=='(':
                if l_remove < remove_left:
                    dfs(i+1, l_counter, r_counter, l_remove+1, r_remove, str_arr)
                return dfs(i+1, l_counter+1, r_counter, l_remove, r_remove, str_arr.append(c))
                
            if r_remove < remove_right:
                dfs(i+1, l_counter, r_counter, l_remove, r_remove+1, str_arr)
                
            if l_counter>r_counter:
                dfs(i+1, l_counter, r_counter+1, l_remove, r_remove, str_arr.append(c))
            return
        
        dfs(0, 0, 0, 0,0, [])
        return list(ans)
