import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {

    int MOD = (int) 1e9 + 7;
    Set<Character> primes = new HashSet<>(Arrays.asList('2', '3', '5', '7'));
    Long[][] dp;
    String s;
    int minLength;

    public int beautifulPartitions(String s, int k, int minLength) {

        if (!primes.contains(s.charAt(0)) || primes.contains(s.charAt(s.length() - 1)))
            return 0; // invalid input

        List<Integer> validCuts = getValidCuts(s, minLength);

        if (validCuts.size() < k - 1 || minLength * k > s.length())
            return 0; // not possible to form k partitions

        if (k == 1)
            return 1; // only possible answer

        this.s = s;
        this.minLength = minLength;
        this.dp = new Long[k][validCuts.size()];

        return (int) this.dfs(validCuts, k - 1, 0, 0);
    }

    private long dfs(List<Integer> validCuts, int k, int idx, int posStartPrevPartition) {

        if (k == 0)
            return 1;
        if (dp[k][idx] != null)
            return dp[k][idx];

        long ans = 0;

        // s.length() - validCuts.get(i) >= k * minLength -> there is enough space to do
        // k partitions of minLength\\
        // k <= validCuts.size() - i -> there is enough partition left
        for (int i = idx; k <= validCuts.size() - i && s.length() - validCuts.get(i) >= k * minLength; i++) {
            if (validCuts.get(i) - posStartPrevPartition < minLength)
                continue;

            ans = (ans + dfs(validCuts, k - 1, i + 1, validCuts.get(i))) % MOD;
        }

        return dp[k][idx] = ans;

    }

    // each index represents an index in s, where the previous char is non prime and
    // i is prime
    private List<Integer> getValidCuts(String s, int minLength) {
        List<Integer> cuts = new ArrayList<>(s.length() / 2 + 1);

        int lastPossibleCut = s.length() - minLength;
        for (int i = minLength; i <= lastPossibleCut; i++) {
            if (!primes.contains(s.charAt(i - 1)) && primes.contains(s.charAt(i)))
                cuts.add(i);
        }
        return cuts;
    }

}