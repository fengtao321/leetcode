class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        ans = []
        
        def dfs(nums_list, arr):
            if not nums_list: 
                ans.append(arr.copy()) 
                return

            for i in range(len(nums_list)):
                num = nums_list[i]
                arr.append(num)
                nums_list.pop(i)
                dfs(nums_list,arr)
                nums_list.insert(i,num)
                arr.pop(-1)
        dfs(nums, [])
        return ans