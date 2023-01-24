class Solution:
    def earliestFullBloom(self, plantTime: List[int], growTime: List[int]) -> int:
        def compare(x, y):
            if x[1]>y[1] or (x[0]+x[1] > y[0] + y[1] and x[1]==y[1]):
                return -1
            return  1
            
        n = len(plantTime)
        array = [[0,0]] * n
        for i in range(n):
            array[i]= [plantTime[i], growTime[i]]
                
        array.sort(key=cmp_to_key(compare))
        
        ans = 0
        time = 0
        for flower in array:
            time += flower[0]
            ans = max(ans, time + flower[1])
        return ans
            
        