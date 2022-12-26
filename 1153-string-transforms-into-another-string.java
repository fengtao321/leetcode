import java.util.HashSet;

class Solution {
    public boolean canConvert(String str1, String str2) {
        if (str1.length() != str2.length())
            return false;

        int[] dictionary = new int[27];
        HashSet<Integer> record = new HashSet<>();
        for (int i = 0; i < str1.length(); i++) {
            int c1 = str1.charAt(i) - 'a' + 1;
            int c2 = str2.charAt(i) - 'a' + 1;

            if (c1 != c2 && (record.size() == 25 && !record.contains(c2))
                    || (dictionary[c1] > 0 && dictionary[c1] != c2))
                return false;

            dictionary[c1] = c2;
            record.add(c2);
        }
        return true;
    }
}