class Solution {
    public boolean canTransform(String start, String end) {
        int size = start.length();
        if (size != end.length())
            return false;

        char[] startArr = start.toCharArray();
        char[] endArr = end.toCharArray();
        int numL = 0;
        int numR = 0;
        for (int i = 0; i < size; i++) {
            if (endArr[i] == 'L') {
                if (numR != 0 || numL < 0)
                    return false;
                numL++;
            }
            if (startArr[i] == 'L') {
                numL--;
            }

            if (startArr[i] == 'R') {
                if (numL != 0 || numR < 0)
                    return false;
                numR++;
            }

            if (endArr[i] == 'R') {

                numR--;
            }
        }

        return numL == 0 && numR == 0;
    }
}