import java.util.Arrays;

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int[] arr = new int[26];
        Arrays.fill(arr, -1);

        int req = 0;

        for (int i = 0; i < s1.length(); i++) {
            int key = s1.charAt(i) - 'a';
            if (arr[key] == -1) {
                req++;
                arr[key] = 1;
            } else {
                arr[key]++;
            }
        }

        int left = 0;
        int[] copy = Arrays.copyOf(arr, arr.length);
        int conditions = req;

        for (int right = 0; right < s2.length(); right++) {
            int key = s2.charAt(right) - 'a';
            if (copy[key] == -1) {
                left = right + 1;
                copy = Arrays.copyOf(arr, arr.length);
                conditions = req;
                continue;
            }

            copy[key]--;

            if (copy[key] == 0) {
                conditions--;
                if (conditions == 0) {
                    return true;
                }

            }

            while (copy[key] < 0) {
                if (copy[s2.charAt(left) - 'a'] == 0)
                    conditions++;
                copy[s2.charAt(left) - 'a']++;
                left++;
            }
        }

        return false;

    }
}