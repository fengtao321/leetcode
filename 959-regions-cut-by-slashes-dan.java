class Solution {

    String[] grid;
    boolean[][][] visited;
    int n;

    public int regionsBySlashes(String[] grid) {
        this.grid = grid;
        this.n = grid.length;
        this.visited = new boolean[n][n][4];

        int ans = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                for (int position = 0; position < 4; position++) {
                    ans += dfs(row, col, position);
                }
            }
        }

        return ans;
    }

    private int dfs(int row, int col, int position) {
        if (Math.min(row, col) < 0 || Math.max(row, col) >= n || visited[row][col][position])
            return 0;

        visited[row][col][position] = true;
        char current = grid[row].charAt(col);

        if (position == 0) {

            dfs(row - 1, col, 2);
            if (current != '\\')
                dfs(row, col, 3);
            if (current != '/')
                dfs(row, col, 1);

        } else if (position == 1) {

            dfs(row, col + 1, 3);
            if (current != '\\')
                dfs(row, col, 2);
            if (current != '/')
                dfs(row, col, 0);

        } else if (position == 2) {

            dfs(row + 1, col, 0);
            if (current != '\\')
                dfs(row, col, 1);
            if (current != '/')
                dfs(row, col, 3);

        } else { // position == 3

            dfs(row, col - 1, 1);
            if (current != '\\')
                dfs(row, col, 0);
            if (current != '/')
                dfs(row, col, 2);
        }

        return 1;
    }
}