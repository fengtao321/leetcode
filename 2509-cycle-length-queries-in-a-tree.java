class Solution {
    public int[] cycleLengthQueries(int n, int[][] queries) {
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = getEdgeNum(queries[i][0], queries[i][1]);
        }

        return ans;
    }

    private int getEdgeNum(int node1, int node2) {
        if (node1 == node2)
            return 0;
        int height = 1;
        while (node1 != node2) {
            if (node1 > node2)
                node1 = (int) (node1 / 2);
            else
                node2 = (int) (node2 / 2);
            ++height;
        }
        return height;
    }
}