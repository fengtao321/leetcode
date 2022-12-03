class Solution {
    public int longestSubsequence(String s, int k) {
        int currentVal = 0;
        int num0s = 0;
        int num1s = 0;
        int pow = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                num0s++;
            } else if (pow < 30 && currentVal + (1 << pow) <= k) {
                num1s++;
                currentVal += (1 << pow);
            }
            pow++;
        }
        return num0s + num1s;
    }
}