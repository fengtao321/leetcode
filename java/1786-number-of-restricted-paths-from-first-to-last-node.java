import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    long[] distances;
    int[] dp;
    HashMap<Integer, List<Node>> map;
    int n;
    int MOD = (int) 1e9 + 7;

    public int countRestrictedPaths(int n, int[][] edges) {
        this.n = n;
        distances = new long[n + 1];
        dp =  new int[n + 1];

        map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<>());
            distances[i] = -1;
            dp[i] = -1;
        }
        distances[n] = 0;
        dp[n] = 1;

        for (int[] edge : edges) {
            map.get(edge[0]).add(new Node(edge[0], edge[1], edge[2]));
            map.get(edge[1]).add(new Node(edge[1], edge[0], edge[2]));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(n,n,0));
        HashSet<Integer> set = new HashSet<>();

        while(!pq.isEmpty() && set.size()<n) {
            Node curr = pq.poll();
            if(set.contains(curr.to))
                continue;

            distances[curr.to] = curr.distance ;
            set.add(curr.to);
            for(Node next: map.get(curr.to)) {
                if(!set.contains(next.to)) {
                    pq.add(new Node(next.from, next.to, next.distance + curr.distance));
                }
            }
        }
        return dfs(1);
    }

    private int dfs(int node) {
        if(dp[node] >=0) {
            return dp[node];
        }

        int sum = 0;
        for(Node next: map.get(node)) {
            if(distances[next.to] < distances[node]) {
                sum = (sum+  dfs(next.to)) % MOD;
            }
        }

        return dp[node] = sum;
    }
}

class Node implements Comparable<Node>{
    int from;
    int to;
    long distance;

    Node(int from, int to, long distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    @Override
    public int compareTo(Node that) {
        if(this.distance < that.distance)
            return -1;
        return 1;
    }
}