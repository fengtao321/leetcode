class Solution {
    String ans;

    public String longestPalindrome(String s) {
        ans = s.substring(0, 1);
        for (int i = 0; i < s.length() - 1; i++) {
            findPalindrome(i, i, s);// odd string
            findPalindrome(i, i + 1, s);// even string
        }
        return ans;
    }

    private void findPalindrome(int l, int r, String s) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        if (ans.length() < r - 1 - l) {
            ans = s.substring(l + 1, r);
        }
    }
}