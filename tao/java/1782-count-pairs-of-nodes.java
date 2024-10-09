import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] countPairs(int n, int[][] edges, int[] queries) {
        int[] inDegree = new int[n + 1];
        HashMap<Integer, Integer>[] common = new HashMap[n + 1];
        int[] ans = new int[queries.length];

        // calculate the number of edges for each node
        // also track how many nodes in common they have
        for (int[] edge : edges) {
            inDegree[edge[0]]++;
            inDegree[edge[1]]++;

            int minEdge = Math.min(edge[0], edge[1]);
            int maxEdge = Math.max(edge[0], edge[1]);
            if (common[minEdge] == null)
                common[minEdge] = new HashMap<>();
            common[minEdge].put(maxEdge, common[minEdge].getOrDefault(maxEdge, 0) + 1);
        }

        int[] sortedEdgeCount = inDegree.clone();
        Arrays.sort(sortedEdgeCount);

        // now let's answer queries in an efficient way

        for (int q = 0; q < queries.length; q++) {

            // we can use 2 pointer approach to count all the pairs using sorted array
            int low = 1, high = sortedEdgeCount.length - 1;
            while (low < high) {
                if (sortedEdgeCount[low] + sortedEdgeCount[high] > queries[q]) {
                    ans[q] += high - low;
                    high--;
                } else {
                    low++;
                }
            }

            // we know the count we got is too high, we need to remove all the ones we
            // counted twice
            // we iterate on unique CONNECTED edges only here and we dont have to generate
            // all combinations
            for (int n1 = 1; n1 < n; n1++) {
                if (common[n1] == null)
                    continue;
                for (Map.Entry<Integer, Integer> entry : common[n1].entrySet()) {
                    int n2 = entry.getKey(), count = entry.getValue();
                    if (inDegree[n1] + inDegree[n2] > queries[q] && inDegree[n1] + inDegree[n2] - count <= queries[q])
                        ans[q]--;
                }
            }
        }

        return ans;
    }
}