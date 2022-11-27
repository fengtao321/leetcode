class Solution {
    public boolean checkRecord(String s) {
        int numA = 0;
        int numL = 0;

        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'A':
                    numA++;
                    if (numA == 2)
                        return false;
                    numL = 0;
                    break;
                case 'L':
                    numL++;
                    if (numL == 3)
                        return false;
                    break;
                default:
                    numL = 0;
            }
        }

        return true;
    }
}