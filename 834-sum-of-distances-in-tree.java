import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;

class Solution {
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        int[][] record = new int[n][n];
        HashMap<Integer, Deque<Integer>> map = new HashMap<>();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayDeque<Integer>());
        }

        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            map.get(x).add(y);
            map.get(y).add(x);
            record[x][y] = 1;
            record[y][x] = 1;
        }

        // printHashMap(map);

        for (int i = 0; i < n; i++) {
            HashSet<Integer> set = new HashSet<>();
            set.add(i);
            Deque<Integer> queue = new ArrayDeque<Integer>();
            for (int k : map.get(i)) {
                queue.add(k);
            }
            int counter = 0;
            while (!queue.isEmpty() || set.size() < n) {
                int size = queue.size();
                counter++;
                // System.out.println("=====");
                // printArrayList(queue);
                // System.out.println(set.toString());

                while (size > 0) {
                    size--;
                    int j = queue.pollFirst();

                    set.add(j);
                    ans[i] += counter;
                    for (int k : map.get(j)) {
                        if (set.contains(k)) {
                            continue;
                        }

                        if (k > i) {
                            queue.add(k);
                        } else {
                            // for (int k = j + 1; k < n; j++) {
                            // if (record[i][k] == 0)
                            // record[i][k] = record[j][k] + counter;
                            // }
                        }
                    }
                }
            }

        }

        return ans;
    }

    private void printArrayList(Deque<Integer> object) {
        object.forEach(value -> System.out.print(value + "->"));
    }

    private void printHashMap(HashMap<Integer, Deque<Integer>> sourceMap) {
        sourceMap.forEach((k, v) -> {
            System.out.println("");
            System.out.print("key: " + k + " value: ");
            printArrayList(v);
            System.out.println("");
        });
    }

}