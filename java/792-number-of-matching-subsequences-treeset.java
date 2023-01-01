import java.util.HashMap;
import java.util.TreeSet;

class Solution {
    HashMap<Character, TreeSet<Integer>> map = new HashMap<>();

    public int numMatchingSubseq(String s, String[] words) {
        initMap(s);
        int ans = 0;
        for (String word : words) {
            if (isSubstring(word))
                ans++;
        }

        return ans;
    }

    private void initMap(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, new TreeSet<>());
            }

            map.get(c).add(i);
        }
    }

    private boolean isSubstring(String s) {
        int last = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (!map.containsKey(c))
                return false;

            TreeSet<Integer> set = map.get(c);
            Integer curr = set.higher(last);
            if (curr == null)
                return false;

            last = curr;
        }
        return true;
    }
}