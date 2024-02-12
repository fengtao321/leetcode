from typing import List

class Solution:
    def carFleet(self, target: int, position: List[int], speed: List[int]) -> int:
        position_speed_tuple = sorted(zip(position, speed))

        pre = ans  = 0
        for i in range(len(position_speed_tuple)-1, -1, -1):
            [p, s] = position_speed_tuple[i]
            time = (target - p)/s
            if time > pre:
                pre  = time
                ans+=1
                
        return ans

c = Solution()
assert c.carFleet(12,[10,8,0,5,3],[2,4,1,1,3]) == 3
assert c.carFleet(10, [3], [3]) == 1