class Solution:
    def diffWaysToCompute(self, expression: str):
        dp = []
        temp = []
        value = 0
        symbol = []
        size = 0
        for char in expression:
            if (char.isdigit()):
                value = value*10 + int(char)
            else:
                if (char == "-"):
                    symbol.append(-1)
                if (char == "+"):
                    symbol.append(0)
                else:
                    symbol.append(1)
                temp.append([value])
                value = 0
                size = size+1
        temp.append([value])
        size = size + 1
        
        dp.append(temp)
        if (size == 1):
            return dp[0][0]
        
        # dp[row][col] = f(sym[col+1], dp[row-1][col], dp[row-2][col+2]) and f(sym[col], dp[row-2][col], dp[row-1])
        for temp_layer in range(1, size):
            temp = []
            for cur_layer in range(0, temp-1):

        

        
    
s = Solution()
print(s.diffWaysToCompute("9"))