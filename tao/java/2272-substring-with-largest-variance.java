class Solution {
    public int largestVariance(String s) {
        var ans = 0;
        for(char maxChar = 'a'; maxChar<='z'; maxChar++) {
            for(char minChar = 'a'; minChar<='z'; minChar++) {
                if (maxChar == minChar) {
                    continue;
                }

                int l = 0;
                int leftRes = 0;
                int rightRes = 0;
                int leftMin = 0;
                boolean hasMin = false;

                for(int r =0 ; r<s.length(); r++) {
                    if (s.charAt(r) == maxChar) {
                        rightRes++;
                    }
                    
                    if (s.charAt(r) == minChar) {
                        rightRes--;
                        hasMin = true;

                        while(l < r) {
                            if (s.charAt(r) == maxChar) {
                                leftRes++;
                                leftMin = Math.min(leftMin, leftRes);
                            }

                            if (s.charAt(r) == minChar) {
                                leftRes--;
                                leftMin = Math.min(leftMin, leftRes);
                            }
                            
                            l++;
                        }
                    }
                    
                    if(rightRes > 0 && hasMin) {
                        ans = Math.max(ans, rightRes - leftMin);
                    }
                }

            }
        }
        return ans;
    }
}