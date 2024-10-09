from collections import deque


class Solution:
    def minimumJumps(self, forbidden: List[int], a: int, b: int, x: int) -> int:
        forbidden = set(forbidden)
        step = -1
        l = min(x + (a + b) * 3, 6000)
        dp = [0] * l
        
        queue = deque([(0, True)])
        while queue:
            size = len(queue)
            step += 1
            
            for i in range(size):
                n, canBackWard = queue.popleft()
                if n == x:
                    return step
                
                if n in forbidden or n >= l or dp[n] > 0:
                    continue
                
                queue.append((n+a, True))
                if canBackWard:
                    dp[n] = step
                    if n-b>-1:
                        queue.append((n-b, False))
        return -1