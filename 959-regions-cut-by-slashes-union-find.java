class Solution {

    int n;
    int ans;
    int[] parents;

    public int regionsBySlashes(String[] grid) {

        this.n = grid.length;
        this.parents = new int[n * n * 4];
        for (int i = 0; i < n * n * 4; i++)
            this.parents[i] = i;

        ans = n * n * 4;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                for (int position = 0; position < 4; position++) {

                    char current = grid[row].charAt(col);

                    if (position == 0) {

                        if (row > 0)
                            union(getIndex(row, col, position), getIndex(row - 1, col, 2));
                        if (current != '\\')
                            union(getIndex(row, col, position), getIndex(row, col, 3));
                        if (current != '/')
                            union(getIndex(row, col, position), getIndex(row, col, 1));

                    } else if (position == 1) {

                        if (col < n - 1)
                            union(getIndex(row, col, position), getIndex(row, col + 1, 3));
                        if (current != '\\')
                            union(getIndex(row, col, position), getIndex(row, col, 2));
                        if (current != '/')
                            union(getIndex(row, col, position), getIndex(row, col, 0));

                    } else if (position == 2) {

                        if (row < n - 1)
                            union(getIndex(row, col, position), getIndex(row + 1, col, 0));
                        if (current != '\\')
                            union(getIndex(row, col, position), getIndex(row, col, 1));
                        if (current != '/')
                            union(getIndex(row, col, position), getIndex(row, col, 3));

                    } else { // position == 3

                        if (col > 0)
                            union(getIndex(row, col, position), getIndex(row, col - 1, 1));
                        if (current != '\\')
                            union(getIndex(row, col, position), getIndex(row, col, 0));
                        if (current != '/')
                            union(getIndex(row, col, position), getIndex(row, col, 2));
                    }

                }
            }
        }

        return ans;

    }

    private boolean union(int nodeA, int nodeB) {
        int parentA = find(nodeA);
        int parentB = find(nodeB);

        if (parentA == parentB)
            return false;

        ans--;
        parents[parentA] = parentB;
        return true;
    }

    private int find(int node) {
        if (parents[node] == node)
            return node;

        return parents[node] = find(parents[node]);
    }

    private int getIndex(int row, int col, int pos) {
        return (row * n + col) * 4 + pos;
    }

}