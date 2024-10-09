class Solution:
    def longestCommonSubpath(self, n: int, paths: List[List[int]]) -> int:
        def rollingHash(span, path):
            mod1, mod2, base = 10**9+7, 10**9+9,  n + 1            
            hash_so_far1,hash_so_far2, p_power1, p_power2 = 0, 0, 1, 1
            
            for i in range(span -1):
                p_power1 = (p_power1 * base) % mod1
                p_power2 = (p_power2 * base) % mod2
            
            hash_set = set()
            for i in range(span):
                hash_so_far1 = (hash_so_far1 * base + path[i]) % mod1
                hash_so_far2 = (hash_so_far2 * base + path[i]) % mod2
            hash_set.add((hash_so_far1, hash_so_far2))
            
            for i in range(span, len(path)):
                hash_so_far1 = ((hash_so_far1 - path[i-span] * p_power1) * base + path[i]) % mod1
                hash_so_far2 = ((hash_so_far2 - path[i-span] * p_power2) * base + path[i]) % mod2
                hash_set.add((hash_so_far1, hash_so_far2))
            return hash_set
        
        def found(span):
            hash_set = rollingHash(span, paths[0])
            
            for i in range(1, len(paths)):
                hash_set = hash_set.intersection(rollingHash(span, paths[i]))
                if len(hash_set) == 0:
                    return False
            return True
            
        
        paths.sort(key = lambda x:len(x))
        l, r = 0, len(paths[0])
        ans = 0
        while l <=r:
            mid = (l + r)//2
            if found(mid):
                ans = max(mid, ans)
                l = mid + 1
            else:
                r = mid - 1
        return ans
            
            
        
            