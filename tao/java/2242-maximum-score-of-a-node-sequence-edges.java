import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

class Solution {
    HashMap<Integer, PriorityQueue<Node>> map;
    int[] scores;

    public int maximumScore(int[] scores, int[][] edges) {
        map = new HashMap<>();
        this.scores = scores;

        // first iteration through edges to create map. The key of the map is From NODE,
        // the value is 4 nodes that connected with FROM NODE with the hightest weight.
        // Use PQ to keep TO NODES, with limitation to 4.
        for (int[] edge : edges) {
            addNodePairToMap(edge[0], edge[1]);
            addNodePairToMap(edge[1], edge[0]);
        }

        // printHashMap(map);

        int max = -1;

        // second iteration through edges to calculate the 4 nodes, and keep the max;

        for (int[] edge : edges) {
            max = Math.max(max, getScore(edge[0], edge[1]));
        }

        return max;
    }

    private int getScore(int from, int to) {
        int sum = -1;

        PriorityQueue<Node> pqFrom = map.get(from);
        PriorityQueue<Node> pqTo = map.get(to);

        if (pqFrom == null || pqTo == null)
            return sum;

        HashSet<Integer> set = new HashSet<>();
        set.add(from);
        set.add(to);

        int scoreBase = scores[from] + scores[to];
        for (Node connectFrom : pqFrom) {
            if (set.contains(connectFrom.index))
                continue;
            set.add(connectFrom.index);

            for (Node connectTo : pqFrom) {
                if (set.contains(connectTo.index))
                    continue;

                sum = Math.max(sum, connectFrom.score + connectTo.score + scoreBase);
            }

            set.remove(connectFrom.index);
        }
        return sum;
    }

    private void addNodePairToMap(int from, int to) {
        PriorityQueue<Node> pq = map.getOrDefault(from, new PriorityQueue<>());
        pq.add(new Node(to, scores[to]));
        if (pq.size() == 4) {
            pq.poll();
        }
        map.put(from, pq);
    }

    private void printHashMap(HashMap<Integer, PriorityQueue<Node>> sourceMap) {
        sourceMap.forEach((k, v) -> {
            System.out.println("");
            System.out.print("key: " + k + " value: ");
            printList(v);
            System.out.println("");
        });
    }

    private void printList(PriorityQueue<Node> object) {
        object.forEach(node -> System.out.print(node.index + "->"));
    }
}

class Node implements Comparable<Node> {
    int index;
    int score;

    Node(int index, int score) {
        this.index = index;
        this.score = score;
    }

    @Override
    public int compareTo(Node that) {
        return this.score - that.score; // ascending order / min Heap
    }
}