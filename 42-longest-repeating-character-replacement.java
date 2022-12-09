import java.util.HashMap;

class Solution {
    public int characterReplacement(String s, int k) {
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0;
        int ans = 0;
        int max = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c, 0) + 1);
            max = Math.max(max, map.get(c));

            if (right + 1 - left - max > k) { // I don't understand here, why not update max??
                char key = s.charAt(left);
                map.put(key, map.get(key) - 1);
                left++;
            }
            ans = Math.max(ans, right + 1 - left);
        }

        return ans;

    }
}