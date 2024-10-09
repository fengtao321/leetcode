import heapq

class Solution:
    def findMaxValueOfEquation(self, points: List[List[int]], k: int) -> int:
        heap = []
        heapq.heapify(heap)
        
        ans  = -1000000000
        for point in points:
            while heap and point[0] - heap[0][1]>k:
                heapq.heappop(heap)

            if heap:   
                ans = max(ans, point[1] + point[0] - heap[0][0])
            heapq.heappush(heap,  [point[0]- point[1], point[0]])
            # print(heap)
            # print(ans)
        return ans