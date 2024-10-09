class Solution {
    public boolean validTree(int n, int[][] edges) {
        int[] rank = new int[n];
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        for (int[] edge : edges) {
            int node0 = getParent(edge[0], parent);
            int node1 = getParent(edge[1], parent);

            if (node0 == node1)
                return false;

            if (rank[node0] >= rank[node1]) {
                rank[node0] += rank[node1];
                parent[edge[1]] = node0;
                parent[node1] = node0; // need to update parent node as well
            } else {
                rank[node1] += rank[node0];
                parent[edge[0]] = node1;
                parent[node0] = node1;
            }
        }
        // System.out.println(Arrays.toString(parent));

        int counter = 0;
        for (int i = 0; i < n; i++) {
            if (parent[i] == i) {
                counter++;
                if (counter > 1)
                    return false;
            }
        }

        return true;
    }

    private int getParent(int node, int[] parent) {
        while (node != parent[node]) {
            node = parent[node];
        }
        return node;
    }
}