import java.util.ArrayList;
import java.util.List;

class Solution {
    String s;
    int minLength;
    Long[][] dp;
    List<Integer> validCuts;
    int k;
    // HashSet<Character> primes = new HashSet<>(Arrays.asList('2', '3', '5', '7'));

    public int beautifulPartitions(String s, int k, int minLength) {
        this.s = s;
        this.k = k;

        if (k * minLength > s.length() || !isPrime(0) || isPrime(s.length() - 1))
            return 0;

        this.minLength = minLength;

        validCuts = new ArrayList<>(s.length() / 2 + 1);
        validCuts.add(0);
        for (int i = 1; i <= s.length() - minLength; i++) {
            if (isPrime(i) && !isPrime(i - 1))
                validCuts.add(i);
        }

        if (validCuts.size() < k || minLength * k > s.length())
            return 0; // not possible to form k partitions

        if (k == 1)
            return 1; // only possible answer

        dp = new Long[k + 1][validCuts.size()];
        return (int) this.dfs(0, 1);
    }

    private long dfs(int start, int counter) {

        if (counter == k)
            return 1;
        if (dp[counter][start] != null)
            return dp[counter][start];

        long ans = 0;

        int left = validCuts.size() - k + counter;
        int minLengthLimit = s.length() - (k - counter) * minLength;

        // s.length() - validCuts.get(i) >= k * minLength -> there is enough space to do
        // k partitions of minLength\\
        // k <= validCuts.size() - i -> there is enough partition left
        for (int i = start + 1; left >= i && minLengthLimit >= validCuts.get(i); i++) {
            if (validCuts.get(i) - validCuts.get(start) >= minLength) {
                ans = (ans + dfs(i, counter + 1)) % 1000000007;
            }
        }

        return dp[counter][start] = ans;

    }

    private boolean isPrime(int i) {
        char c = s.charAt(i);
        return c == '2' || c == '3' || c == '5' || c == '7';
    }
}