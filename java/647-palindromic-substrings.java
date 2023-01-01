class Solution {
    int ans = 0;

    public int countSubstrings(String s) {
        for (int i = 0; i < s.length(); i++) {
            findPalindrome(i, i, s);// odd string
            findPalindrome(i, i + 1, s);// even string
        }
        return ans;
    }

    private void findPalindrome(int l, int r, String s) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
            ans++;
        }
    }
}