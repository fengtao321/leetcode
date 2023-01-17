from collections import defaultdict


class Solution:
    def numberOfGoodPaths(self, vals: List[int], edges: List[List[int]]) -> int:
        n = len(vals)
        parent = list(range(n))
       
        def find(x: int) -> int:
            if parent[x] != x:
                parent[x] = find(parent[x])
            return parent[x]

        hashmap = defaultdict(list)
        for x, y in edges:
            hashmap[x].append(y)
            hashmap[y].append(x)
          
        ans = n
        size = [1] * n
        for vx, x in sorted(zip(vals, range(n))):
            px = find(x)
            for y in hashmap[x]:
                py = find(y)
                if py == px or vals[py] > vx: continue  # 只考虑最大节点值比 vx 小的连通块
                if vals[py] == vx:  # 可以构成好路径
                    ans += size[px] * size[py]  # 乘法原理
                    size[px] += size[py]  # 统计连通块内节点值等于 vx 的节点个数
                parent[py] = px  # 把小的节点值合并到大的节点值上
        return ans