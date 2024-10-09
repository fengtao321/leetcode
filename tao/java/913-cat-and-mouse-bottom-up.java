import java.util.Arrays;

class Solution {
    int[][][] cache;
    int[][] graph;
    private static final int DRAW = 0;
    private static final int CAT_WIN = 2;
    private static final int MOUSE_WIN = 1;

    private static final int IS_MOUSE = 0;
    private static final int IS_CAT = 1;

    private static final int HOLE = 0;

    public int catMouseGame(int[][] graph) {
        this.graph = graph;
        initMatrix(graph.length);
        return dfs(2, 1, 0);
    }

    private int dfs(int cat, int mouse, int step) {
        if (step >= graph.length * 5) {
            return DRAW;
        }
        if (cache[cat][mouse][step] != -1) {
            return cache[cat][mouse][step];
        }

        if (mouse == HOLE) {
            return cache[cat][mouse][step] = MOUSE_WIN;
        }
        if (cat == mouse) {
            return cache[cat][mouse][step] = CAT_WIN;
        }

        int player = step & 1; // 0 is mouse, 1 is cat
        int ans = (player == IS_MOUSE) ? CAT_WIN : MOUSE_WIN;// default value, worst result
        int[] nextPositions = (player == IS_MOUSE) ? graph[mouse] : graph[cat];
        for (int nextPosition : nextPositions) {
            if (player == IS_CAT && nextPosition == HOLE)
                continue;

            int whoWillWin = (player == IS_MOUSE) ? dfs(cat, nextPosition, step + 1)
                    : dfs(nextPosition, mouse, step + 1);
            if (player == IS_MOUSE && whoWillWin == CAT_WIN) {
                continue;
            }
            if (player == IS_MOUSE && whoWillWin == MOUSE_WIN) {
                return cache[cat][mouse][step] = MOUSE_WIN;
            }

            if (player == IS_CAT && whoWillWin == MOUSE_WIN) {
                continue;
            }

            if (player == IS_CAT && whoWillWin == CAT_WIN) {
                return cache[cat][mouse][step] = CAT_WIN;
            }

            if (whoWillWin == DRAW)
                ans = DRAW;

        }

        return cache[cat][mouse][step] = ans;

    }

    private void initMatrix(int n) {
        cache = new int[n][n][5 * n]; // cat node, mouse node, who move? -> 1 is mouse, 0 is cat
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(cache[i][j], -1);
            }
        }
    }
}
