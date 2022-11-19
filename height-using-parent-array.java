// https://practice.geeksforgeeks.org/problems/height-using-parent-array4103/1

class Solution {
    static int findHeight(int N, int[] arr) {
        // code here
        int[] heights = new int[N];
        int height = 0;
        for (int i = 0; i < N; i++) {
            dfs(i, heights, arr);
            height = heights[i] > height ? heights[i] : height;
        }

        return height;
    }

    static void dfs(int i, int[] heights, int[] arr) {
        if (heights[i] > 0)
            return;

        int index = arr[i];
        if (index == -1) {
            heights[i] = 1;
            return;
        }

        if (heights[index] == 0) {
            dfs(index, heights, arr);
        }

        heights[i] = heights[index] + 1;
        return;
    }
}