class Solution:
    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
        m,n = len(nums1), len(nums2)
        if m>n: return self.findMedianSortedArrays(nums2, nums1)
        
        l, r, total = 0, m-1, m+n
        half = total//2
        while True:
            i = (l+r)//2
            j = half - i -2
            
            num1_left = nums1[i] if i>=0 else float("-infinity")
            num1_right = nums1[i+1] if i+1<m else float("infinity")
            num2_left = nums1[j] if j>=0 else float("-infinity")
            num2_right = nums1[j+1] if j+1<n else float("infinity")
            
            if num1_left<=num2_right and num2_left<=num1_right:
                if total%2:
                    return min(num1_right, num2_right)
                return (max(num1_left, num2_left) + min(num1_right, num2_right)) / 2
            if num1_left>num2_right:
                r=i-1
            else:
                l=i+1
            
        
        
       