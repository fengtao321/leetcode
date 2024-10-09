class Solution {
    public int maxScore(String s) {
        int max = 0;
        int numOfOne = 0;
        int numOfZero = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                numOfOne++;
            }
        }

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                numOfOne--;
            } else {
                numOfZero++;
            }

            max = Math.max(max, numOfOne + numOfZero);
        }

        return max;
    }
}