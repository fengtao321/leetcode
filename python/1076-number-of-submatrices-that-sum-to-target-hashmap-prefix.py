class Solution:
    def numSubmatrixSumTarget(self, matrix: List[List[int]], target: int) -> int:
        m, n, ans = len(matrix), len(matrix[0]), 0
        prefix = [[0] * (n+1) for _ in range(m+1)]
        
        for i in range(1, m+1):
            for j in range(1, n+1):
                prefix[i][j] = matrix[i-1][j-1] + prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1]
        
        for i in range(1,m+1):
            for p in range(i, m+1):
                hash_map = defaultdict(int)
                hash_map[0] = 1
                for q in range(1, n+1):
                    curr = prefix[p][q] - prefix[i-1][q]
                    if  curr - target  in hash_map:
                        # print("i = ", i, ", p = ", p, ", q = ", q, ", curr = ", curr)
                        ans+=hash_map[curr - target]
                    hash_map[curr] += 1
        return ans
                