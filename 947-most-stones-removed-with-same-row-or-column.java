import java.util.HashMap;

class Solution {
    public int removeStones(int[][] stones) {
        int m = stones.length;
        int ans = 0;
        HashMap<Integer, Integer> xMap = new HashMap<>();
        HashMap<Integer, Integer> yMap = new HashMap<>();

        UnionFind uf = new UnionFind(m);
        for (int i = 0; i < m; i++) {
            int[] stone = stones[i];
            int x = stone[0];
            int y = stone[1];

            if (!xMap.containsKey(x)) {
                xMap.put(x, i);
            }
            x = xMap.get(x);

            if (!yMap.containsKey(y)) {
                yMap.put(y, i);
            }
            y = yMap.get(y);

            if (uf.connect(i, y)) {
                ans++;
            }

            if (uf.connect(i, x)) {
                ans++;
            }
        }

        return ans;
    }
}

class UnionFind {
    int[] union;

    UnionFind(int size) {
        union = new int[size];
        for (int i = 0; i < size; i++) {
            union[i] = i;
        }
    }

    int findParent(int i) {
        if (union[i] == i)
            return i;
        return findParent(union[i]);
    }

    boolean connect(int i, int j) {
        int parentI = findParent(i);
        int parentJ = findParent(j);

        if (parentI == parentJ)
            return false;

        union[parentJ] = parentI;
        return true;
    }

    // int getUnionFind() {
    // System.out.println(Arrays.toString(union));
    // int num = 0;
    // for (int i = 0; i < union.length; i++) {
    // if (union[i] == i)
    // num++;
    // }
    // return num;
    // }
}