import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

class Solution {
    TreeMap<Integer, List<int[]>> map;
    int[][] matrix;
    int[][] ans;
    int[] maxRow;
    int[] maxCol;
    int m;
    int n;

    public int[][] matrixRankTransform(int[][] matrix) {
        this.matrix = matrix;
        m = matrix.length;
        n = matrix[0].length;
        ans = new int[m][n];
        maxRow = new int[m];
        maxCol = new int[n];
        map = initMap();
        // printTreeMap(map);

        for (Entry<Integer, List<int[]>> entry : map.entrySet()) {
            List<int[]> indexList = entry.getValue();

            if (indexList.size() == 1) {
                int[] node = indexList.get(0);
                fillCell(node[0], node[1], 0);
                continue;
            }

            UnionFind uf = new UnionFind(m + n);
            for (int i = 0; i < indexList.size(); i++) {
                int[] node = indexList.get(i);
                uf.connect(node[0], m + node[1]);
            }

            for (List<int[]> group : uf.getUnion(indexList)) {
                // System.out.println("");
                // printArrayList(group);
                int rank = 0;
                for (int[] arr : group) {
                    rank = Math.max(rank, Math.max(maxRow[arr[0]], maxCol[arr[1]]) + 1);
                }

                // System.out.println(rank);
                for (int[] arr : group) {
                    fillCell(arr[0], arr[1], rank);
                }
            }

        }

        return ans;
    }

    private void fillCell(int i, int j, int rank) {
        rank = rank > 0 ? rank : Math.max(maxRow[i], maxCol[j]) + 1;
        ans[i][j] = rank;
        maxRow[i] = rank;
        maxCol[j] = rank;
    }

    private TreeMap<Integer, List<int[]>> initMap() {
        TreeMap<Integer, List<int[]>> map = new TreeMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int key = matrix[i][j];
                if (!map.containsKey(key)) {
                    map.put(key, new ArrayList<>());
                }

                map.get(key).add(new int[] { i, j });
            }
        }
        return map;
    }

    private void printTreeMap(TreeMap<Integer, List<int[]>> sourceMap) {
        sourceMap.forEach((k, v) -> {
            System.out.println("");
            System.out.print("key: " + k + " value: ");
            printArrayList(v);
            System.out.println("");
        });
    }

    private void printArrayList(List<int[]> object) {
        object.forEach(value -> System.out.print(value[0] + "," + value[1] + "->"));
    }
}

class UnionFind {
    int[] parent;
    HashMap<Integer, List<int[]>> map = new HashMap<>();

    UnionFind(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    public int findParent(int index) {
        return (index == parent[index]) ? index : findParent(parent[index]);
    }

    public void connect(int a, int b) {
        int parentA = findParent(a);
        int parentB = findParent(b);

        if (parentA == parentB)
            return;

        parent[parentA] = parentB;
        // System.out.println(Arrays.toString(parent));
    }

    public Collection<List<int[]>> getUnion(List<int[]> indexList) {
        // System.out.println(Arrays.toString(parent));
        for (int[] node : indexList) {
            int parentA = findParent(node[0]);
            if (!map.containsKey(parentA)) {
                map.put(parentA, new ArrayList<>());
            }

            map.get(parentA).add(new int[] { node[0], node[1] });
        }
        return map.values();
    }

}