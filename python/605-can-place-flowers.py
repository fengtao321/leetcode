class Solution:
    def canPlaceFlowers(self, flowerbed: List[int], n: int) -> bool:
        if n==0:
            return True
        
        canPlant = True
        
        for i in range(len(flowerbed)):
            curr = flowerbed [i]
            if curr == 1:
                canPlant = False
                continue
            
            if not canPlant:
                canPlant = True
                continue
            
            if i+1==len(flowerbed) or flowerbed[i+1]==0:
                n -=1
                canPlant = False
        return n==0
            