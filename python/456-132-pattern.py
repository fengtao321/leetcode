class Solution:
    def find132pattern(self, nums: List[int]) -> bool:
        if len(nums) < 3: 
            return False
        heap_c, heap_b = [], []
        for i in range(len(nums)-1, -1, -1):
            c = nums[i]
            if heap_b and c<-heap_b[0]:
                return True
            while heap_c and heap_c[0]<c:
                heappush(heap_b, -heappop(heap_c))
            heappush(heap_c, c)
            
        return False
            