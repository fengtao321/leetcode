import java.util.HashSet;

class Solution {
    HashSet<String> set = new HashSet<>();
    char[][] board;
    int rowNum;
    int colNum;

    public void solve(char[][] board) {
        this.board = board;
        this.rowNum = board.length;
        this.colNum = board[0].length;

        for (int i = 0; i < rowNum; i++) {
            traverseBoard(i, 0);
            traverseBoard(i, colNum - 1);
        }

        for (int j = 1; j < colNum - 1; j++) {
            traverseBoard(0, j);
            traverseBoard(rowNum - 1, j);
        }

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < rowNum; j++) {
                if (board[i][j] == 'O' && !set.contains(i + "-" + j)) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void traverseBoard(int i, int j) {
        if (i < 0 || i == rowNum || j < 0 || j == colNum || board[i][j] == 'X' || set.contains(i + "-" + j))
            return;

        set.add(i + "-" + j);
        traverseBoard(i + 1, j);
        traverseBoard(i - 1, j);
        traverseBoard(i, j + 1);
        traverseBoard(i, j - 1);
    }
}