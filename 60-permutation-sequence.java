class Solution {
    int counter = 0;
    int ans = 0;
    int[] record;

    public String getPermutation(int n, int k) {
        record = new int[n + 1];
        dfs(n, k, 0, 0);
        return Integer.toString(ans);
    }

    private void dfs(int n, int k, int num, int size) {
        // System.out.println(num);
        // System.out.println("size = " + size);

        if (size == n) {
            counter++;
            if (counter == k) {
                ans = num;
            }
            return;
        }

        size++;
        num = num * 10;
        for (int i = 1; i <= n; i++) {
            if (ans > 0) {
                return;
            }
            if (record[i] > 0)
                continue;
            record[i] = 1;
            dfs(n, k, num + i, size);
            record[i] = 0;
        }
    }
}