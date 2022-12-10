import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public String minWindow(String s, String t) {
        s = s.toUpperCase();
        t = t.toUpperCase();
        int[] tArray = new int[26];
        int totalConditions = 0;
        Deque<Integer> queue = new ArrayDeque<>(); // record the index of left

        for (int i = 0; i < t.length(); i++) {
            int key = t.charAt(i) - 'A';
            if (tArray[key] == 0) {
                totalConditions++;
            }
            tArray[key]++;
        }

        int[] sArray = new int[26];
        int conditions = 0;

        String ans = "";
        for (int r = 0; r < s.length(); r++) {
            int key = s.charAt(r) - 'A';
            if (tArray[key] == 0)
                continue;

            queue.addLast(r);
            sArray[key]++;

            if (sArray[key] == tArray[key]) {
                conditions++;
                while (conditions == totalConditions) {
                    // update length
                    int l = queue.pollFirst();
                    System.out.println(l);
                    ans = (r - l + 1 < ans.length() || ans.length() == 0) ? s.substring(l, r + 1) : ans;

                    // remove the most left char from the substring, and check if still meet the
                    // conditions
                    int leftKey = s.charAt(l) - 'A';
                    if (sArray[leftKey] == tArray[leftKey]) {
                        conditions--;
                    }
                    sArray[leftKey]--;
                }
            }
        }

        return ans;
    }
}