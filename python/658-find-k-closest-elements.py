class Solution:
    def findClosestElements(self, arr: List[int], k: int, x: int) -> List[int]:
        l, r = 0, k
        while r<len(arr):
            if abs(arr[r]-x) >= abs(arr[l]-x) and arr[r] >=x:
                break 
            l+=1
            r+=1
        return arr[l:r]
