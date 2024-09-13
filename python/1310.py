class Solution:
    def xorQueries(self, arr: List[int], queries: List[List[int]]) -> List[int]:
        N = len(arr)
        res1 = [0] * (N + 1)
        res2 = [0] * (N + 1)
        total = 0


        for i in range(N):
            res1[i+1] = res1[i] ^ arr[i]
            res2[N-i-1] = res2[N-i] ^ arr[N-i-1]
            total^=arr[i]
        
        ans = [0] *  len(queries)

        for i, [start, end] in enumerate(queries):
            ans[i] = total ^ res1[start] ^ res2[end+1]

        return ans