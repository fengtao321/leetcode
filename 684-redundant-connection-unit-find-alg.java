class Solution {
    int[] units;
    int[] ranks;

    public int[] findRedundantConnection(int[][] edges) {
        int length = edges.length + 1;
        units = new int[length];
        ranks = new int[length];

        for (int i = 1; i < length; i++) {
            units[i] = i;
            ranks[i] = 1;
        }

        for (int[] edge : edges) {
            int leftN = findParent(edge[0]);
            int rightN = findParent(edge[1]);

            if (leftN == rightN) { // left and right nodes have the same parent
                return edge;
            }

            int leftRank = ranks[leftN];
            int rightRank = ranks[rightN];

            if (leftRank >= rightRank) {
                ranks[leftN] = leftRank + rightRank;
                units[rightN] = leftN;
            } else {
                ranks[rightN] = leftRank + rightRank;
                units[leftN] = rightN;
            }
        }

        return null;
    }

    private int findParent(int node) {
        if (node == units[node]) // index same as node number, stop
            return node;

        return findParent(units[node]);
    }
}