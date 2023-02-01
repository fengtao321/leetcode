class Solution:
    def simplifyPath(self, path: str) -> str:
        arr = list(path.split("/"))
        ans = []

        for ele in arr:
            if not ele or ele=="." or (ele == ".." and not ans):
                continue
            if ele == "..":
                ans.pop()
            else:
                ans.append(ele)

        return '/'+'/'.join(ans)