class Solution:
    def replaceElements(self, arr: List[int]) -> List[int]:
        last = arr[-1]
        arr[-1] = -1  
        for i in range(len(arr)-2, -1, -1):
            temp = arr[i]
            arr[i] = max(last,arr[i+1])
            last = temp
           
        return arr