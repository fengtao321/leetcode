class Solution:
    def asteroidCollision(self, asteroids: List[int]) -> List[int]:
        stack = []
        for asteroid in asteroids:
            isSmashed = False
            while stack and stack[-1] > 0 and asteroid < 0:
                if stack[-1] > -asteroid:
                    isSmashed = True
                    break
                if stack.pop(-1) == -asteroid:
                    isSmashed = True
                    break

            if not isSmashed:
                stack.append(asteroid)
                                    
        return stack
                
                    