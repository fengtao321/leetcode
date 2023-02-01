class Solution:
    def canSeePersonsCount(self, heights: List[int]) -> List[int]:
        N = len(heights)
        ans, stack = [0] * N, [heights[N-1]]
        for i in range(N-2, -1, -1):
            counter = 0
            while stack and stack[0] < heights[i]:
                counter += 1
                stack.pop(0)
            ans[i] = counter + 1 if stack else counter
            stack.insert(0, heights[i])
        return ans