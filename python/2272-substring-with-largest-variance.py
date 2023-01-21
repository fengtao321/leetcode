class Solution {
    public int largestVariance(String s) {
        Set<Character> uniques = new HashSet<>();
        int[] freq = new int[26];
        
        for (char c : s.toCharArray()) {
            uniques.add(c);
            freq[c-'a']++;
        }
        int max = 0;
        for (char a : uniques) {
            for (char b : uniques) {
                
                int countA = 0, countB = 0, freqA = freq[a-'a'], freqB = freq[b-'a'];
                if (a == b || freqA == 0 || freqB == 0) continue;
                
                for (char c : s.toCharArray()) {
                   
                    if (a == c) 
                        countA++;
                    else if (b == c) {
                        countB--;
                        freqB--;
                    }
                    
                    if (countA + countB < 0 && freqB > 0) {
                        //current total is negative and it would be optimal to start at a A
                        //but we cannot do if no B left.
                        countA = 0;
                        countB = 0;
                    }
                    
                    if (countA != 0 && countB != 0)
                        max = Math.max(max, countA + countB);
                    
                }
            }
        }
        return max;

    }
}