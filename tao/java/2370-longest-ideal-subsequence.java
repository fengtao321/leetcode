class Solution {
    public int longestIdealString(String s, int k) {
        int[] map = new int[26];
        int max = 0;

        for (int i = 0; i < s.length(); i++) {
            int length = 0;
            int curr = s.charAt(i) - 'a';
            // System.out.println(curr);

            // get the range of one character before current
            int minChar = Math.max(0, curr - k);
            int maxChar = Math.min(25, curr + k);

            // go through the range, to get the max length of each character within the
            // range
            for (int j = minChar; j <= maxChar; j++) {
                length = Math.max(length, map[j]);
            }
            length++;
            map[curr] = length;
            if (length > max)
                max = length;

        }
        // System.out.println(Arrays.toString(map));
        return max;
    }
}
