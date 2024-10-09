class Solution:
    def generate(self, numRows: int) -> List[List[int]]:
        ans =  [[1]]     
        if numRows == 1:
            return ans
    
        for i in range(2, numRows+1):
            curr = [0] + ans[-1] + [0]
            row = []
            for j in range(1, len(curr)):
                row.append(curr[j-1] + curr[j])

            ans.append(row)
           
        return ans 