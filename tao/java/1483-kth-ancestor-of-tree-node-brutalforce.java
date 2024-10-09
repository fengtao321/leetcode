import java.util.ArrayList;

class TreeAncestor {
    int[] parent;
    int[] tree;

    public TreeAncestor(int n, int[] parent) {
        this.parent = parent;
        tree = new int[n];
        for (int i = 0; i < n; i++) {
            tree[i] = i;
        }
    }

    public int getKthAncestor(int node, int k) {
        int index = node;
        while (k > 0 && index >= 0) {
            index = parent[index];
            k = k - 1;
        }

        return index >= 0 ? tree[index] : -1;
    }
}