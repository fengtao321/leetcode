import java.util.Arrays;

class TreeAncestor {
    int[] parent;
    int[][] tree;
    int[] heights;

    public TreeAncestor(int n, int[] parent) {
        this.parent = parent;
        tree = new int[n][16];
        heights = new int[n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(tree[i], -1);
            tree[i][0] = parent[i];
            getHeight(i);
        }

        for (int j = 1; j < 16; j++) { // start from first column
            for (int i = 0; i < n; i++) {
                if (tree[i][j - 1] > -1) {
                    tree[i][j] = tree[tree[i][j - 1]][j - 1];
                }
            }
        }

        // System.out.println(Arrays.toString(heights));
        // for (int i = 0; i < n; i++) {
        // System.out.println(Arrays.toString(tree[i]));
        // }
    }

    public int getKthAncestor(int node, int k) {
        int height = heights[node] - 1;
        if (k > height)
            return -1;
        if (k == height)
            return 0; // root of the tree is node 0

        for (int i = 15; (i >= 0) && (node > -1) && (k > 0); i--) {
            if (((1 << i) & k) > 0) { // get the largest mode
                node = tree[node][i];
            }
        }

        return node;
    }

    private void getHeight(int i) {
        if (heights[i] > 0)
            return;

        int parentIndex = parent[i];
        if (parentIndex == -1) {
            heights[i] = 1; // root height = 1
            return;
        }

        if (heights[parentIndex] == 0) {
            getHeight(parentIndex);
        }
        heights[i] = heights[parentIndex] + 1;
        return;
    }
}
/**
 * Your TreeAncestor object will be instantiated and called as such:
 * TreeAncestor obj = new TreeAncestor(n, parent);
 * int param_1 = obj.getKthAncestor(node,k);
 */