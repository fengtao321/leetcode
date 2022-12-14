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

        List<Node> list = map.get(1);
        for (int i = 0; i < list.size(); i++) {
            ans += getRestrictedPath(list.get(i).to, 1);
            ans = ans % 1000000007;
        }

        return ans;
    }

    private int getRestrictedPath(int curr, int last) {
        if (distances[curr] == 0)
            return 1;
        if (distances[last] <= distances[curr])
            return 0;

        List<Node> list = map.get(curr);
        int counter = 0;
        for (int i = 0; i < list.size(); i++) {
            counter += getRestrictedPath(list.get(i).to, curr);
        }

        return counter;
    }

    private int getNodeDistances(int index, HashSet<Integer> set) {
        if (distances[index] > -1)
            return distances[index];

        List<Node> list = map.get(index);
        int min = Integer.MAX_VALUE;
        set.add(index);

        for (int i = 0; i < list.size(); i++) {
            Node node = list.get(i);
            if (set.contains(node.to))
                continue;
            getNodeDistances(node.to, set);
            min = Math.min(min, node.distance + distances[node.to]);
        }

        set.remove(index);
        return min;
    }

    private void printArrayList(List<Node> object) {
        object.forEach(value -> System.out
                .print("from:" + value.from + "; to:" + value.to + "; distance:" + value.distance + "->"));
        System.out.println("");
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