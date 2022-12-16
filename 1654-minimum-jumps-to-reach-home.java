class Solution {
    int[] ans;
    int a;
    int b;
    int x;

    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        if (x == 0)
            return 0;
        ans = new int[Math.min(x + (a + b) * 3, 6000)];
        this.a = a;
        this.b = b;
        this.x = x;

        for (int i = 0; i < forbidden.length; i++) {
            if (forbidden[i] < ans.length)
                ans[forbidden[i]] = -1;
        }

        dp(0, 0, true);

        return ans[x] == 0 ? -1 : Math.abs(ans[x]);

    }

    private void dp(int i, int jumps, boolean back) {
        if (i < 0 || i >= ans.length || ans[i] == -1 || ans[i] > 0 || (back == false && ans[i] < -1))
            return;

        ans[i] = (back == true) ? jumps : -jumps;

        // System.out.println(Arrays.toString(ans));

        if (i == x) {
            return;
        }

        dp(i + a, jumps + 1, true);
        if (back)
            dp(i - b, jumps + 1, false);
    }
}