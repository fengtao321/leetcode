import java.util.HashSet;

class Solution {
    public int countTriplets(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        set.add(0);

        int ans = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int last = 0;
            for (int j = i; j < arr.length; j++) {
                last = last ^ arr[j];
                if (set.contains(last)) {
                    ans++;
                } else {
                    set.add(last);
                }
            }

        }

        return ans;
    }
}