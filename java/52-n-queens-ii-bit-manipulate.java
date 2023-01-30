class Solution {
    int total = 0;
    public int totalNQueens(int n) {
        backtrack(0, n, 0, 0, 0);
        return total;
    }
    
    private void backtrack(int row, int n, int colMask, int diag1Mask, int diag2Mask) {
        if (row == n) {
            total++;
            return;
        }
        
        for (int j = 0; j < n; j++) {
            
            if ((colMask & (1 << j)) == 0 && (diag1Mask & (1 << j)) == 0 && (diag2Mask & (1 << j)) == 0) {
                int newColMask = colMask | (1 << j);
                int newDiag1Mask = diag1Mask | (1 << j);
                int newDiag2Mask = diag2Mask | (1 << j);
                
                //(newDiag1Mask >> 1) means we reduce the number. so if on row 1 we have 00100, on row 2 we have 01000, then 10000
                //(newDiag2Mask << 1) does the opposite. The number grows to a 1 moves the other way
                backtrack(row+1, n, newColMask, (newDiag1Mask >> 1), (newDiag2Mask << 1));
            }
            
        }
    }
        
}