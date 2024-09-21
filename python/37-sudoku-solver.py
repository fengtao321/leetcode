class Solution:
    def solveSudoku(self, board: List[List[str]]) -> None:
        rows = defaultdict(set)
        cols = defaultdict(set)
        blocks = defaultdict(set)
        filled = set()
        
        l = [str(i) for i in range(1, 10)]
        
        for i in range(9):
            rows[i] = set(l)
            cols[i] = set(l)
            blocks[i] = set(l)
        
        for i in range(9):
            for j in range(9):
                if board[i][j]!='.': 
                    rows[i].remove(board[i][j])
                    cols[j].remove(board[i][j])
                    blocks[(i//3)*3 + j//3].remove(board[i][j])
                    filled.add((i, j))
                    
        def backtrack(i, j):
            if i==9:return True
            if j==9: return backtrack(i+1, 0)
            if (i, j) in filled: return backtrack(i, j+1)
            
            block_index = (i//3)*3 + j//3
            
            for num in rows[i]&cols[j]&blocks[block_index]:
                rows[i].remove(num)
                cols[j].remove(num)
                blocks[block_index].remove(num)
                
                board[i][j] = num
                
                if backtrack(i, j+1): return True
                rows[i].add(num)
                cols[j].add(num)
                blocks[block_index].add(num)
                board[i][j] = '.'
                
            if board[i][j]=='.': return False
            
        backtrack(0, 0)
                

                    
                
        
        
                    