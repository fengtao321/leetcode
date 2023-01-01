class Solution {
    public int longestSubsequence(String s, int k) {
        String k2 = Integer.toBinaryString(k);
        if (k2.length() > s.length())
            return s.length();

        int counter = 0;
        int keyPoint = s.length() - k2.length();
        for (int i = s.length() - 1; i >= 0; i--) {
            // count all 0, and the 1s, that is less than k
            if (s.charAt(i) == '0' || i > keyPoint) {
                counter++;
                continue;
            }

            // check if the substring of s, which is from right, and has same length as k,
            // if substring is less than k, add this character, otherwise, ignore it.
            if (i == keyPoint && compareNum(k, s.substring(keyPoint, s.length()))) {
                counter++;
            }

        }

        return counter;
    }

    private boolean compareNum(int k, String s) {
        int s10 = Integer.parseInt(s, 2);
        return s10 <= k;
    }
}