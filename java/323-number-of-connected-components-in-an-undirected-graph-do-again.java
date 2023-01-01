class Solution {
    int[] parents;
    int[] ranks;

    public int countComponents(int n, int[][] edges) {
        parents = new int[n];
        ranks = new int[n];

        for (int i = 0; i < n; i++) {
            parents[i] = i;
            ranks[i] = 1;
        }

        for (int[] edge : edges) {
            int p1 = findParent(edge[0]);
            int p2 = findParent(edge[1]);

            if (p1 == p2)
                continue;

            if (ranks[p1] > ranks[p2]) {
                ranks[p1] += ranks[p2];
                parents[edge[1]] = edge[0];
                parents[p2] = p1;
            } else {
                ranks[p2] += ranks[p1];
                parents[edge[0]] = edge[1];
                parents[p1] = p2;
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (parents[i] == i) {
                ans++;
            }
        }
        return ans;
    }

    private int findParent(int node) {
        return parents[node] == node ? node : findParent(parents[node]);
    }
}