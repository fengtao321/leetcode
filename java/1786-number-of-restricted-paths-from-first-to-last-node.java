import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

class Solution {
    int[] distances;
    HashMap<Integer, List<Node>> map;
    int n;

    public int countRestrictedPaths(int n, int[][] edges) {
        this.n = n;
        distances = new int[n + 1];
        map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<>());
            distances[i] = -1;
        }
        distances[n] = 0;

        for (int[] edge : edges) {
            map.get(edge[0]).add(new Node(edge[0], edge[1], edge[2]));
            map.get(edge[1]).add(new Node(edge[1], edge[0], edge[2]));
        }

        for (int i = 1; i < n; i++) {
            distances[i] = getNodeDistances(i, new HashSet<>());
            System.out.println(Arrays.toString(distances));
        }

        int ans = 0;

        return ans;
    }
}

class Node {
    int from;
    int to;
    int distance;

    Node(int from, int to, int distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }
}