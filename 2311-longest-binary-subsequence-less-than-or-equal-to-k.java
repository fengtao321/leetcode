class Solution {
    public int longestSubsequence(String s, int k) {
        String binaryK = Integer.toBinaryString(k);
        int lengthK = binaryK.length();
        if (lengthK > s.length())
            return s.length();

        // compare from 0 to lengthK-1
        boolean isKGreater = true;
        int diff = s.length() - lengthK;
        for (int i = 0; i < lengthK; i++) {
            int kNum = binaryK.charAt(i) - '0';
            int sNum = s.charAt(i + diff) - '0';

            if (kNum == sNum)
                continue;

            if (kNum < sNum) {
                isKGreater = false;
            }

            break;
        }

        // System.out.println(isKGreater);
        int counter = isKGreater ? binaryK.length() : binaryK.length() - 1;

        for (int i = 0; i < diff; i++) {
            if (s.charAt(i) == '0') {
                // System.out.println(i);
                counter++;
            }

        }

        return counter;

    }
}