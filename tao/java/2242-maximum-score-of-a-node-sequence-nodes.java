import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

class Solution {
    List<Node> nodeList;
    HashMap<Integer, List<Node>> map;

    public int maximumScore(int[] scores, int[][] edges) {
        nodeList = new ArrayList<>();
        map = new HashMap<>();

        for (int i = 0; i < scores.length; i++) {
            nodeList.add(new Node(i, scores[i]));
        }
        Collections.sort(nodeList);

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];

            List<Node> list = map.getOrDefault(from, new ArrayList<>());
            list.add(new Node(to, scores[to]));
            map.put(from, list);

            list = map.getOrDefault(to, new ArrayList<>());
            list.add(new Node(from, scores[from]));
            map.put(to, list);
        }

        // printHashMap(map);

        int max = -1;

        for (int i = 0; i < scores.length; i++) {
            // if (topFourNodeSum(i) <= max)
            // return max;

            max = Math.max(max, getScore(nodeList.get(i), 4, new HashSet<>()));
        }

        return max;
    }

    private int getScore(Node from, int times, HashSet<Integer> set) {
        if (times == 0)
            return 0;

        int max = -1;
        List<Node> queue = map.get(from.index);
        if (queue == null)
            return max;

        set.add(from.index);
        for (int i = 0; i < queue.size(); i++) {
            Node node = queue.get(i);
            if (times > 1 && set.contains(node.index))
                continue;

            int score = getScore(node, times - 1, set);
            if (score >= 0) {
                score += from.score;
                max = Math.max(max, score);
            }
        }
        set.remove(from.index);
        return max;
    }

    private int topFourNodeSum(int start) {
        int sum = 0;
        for (int i = start; i < start + 4; i++) {
            sum += nodeList.get(i).score;
        }
        return sum;
    }

    private void printHashMap(HashMap<Integer, List<Node>> sourceMap) {
        sourceMap.forEach((k, v) -> {
            System.out.println("");
            System.out.print("key: " + k + " value: ");
            printList(v);
            System.out.println("");
        });
    }

    private void printList(List<Node> object) {
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
        return that.score - this.score; // descending order / maximum Heap
    }
}