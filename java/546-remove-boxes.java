class Solution {
    
    int[][][] dp;
    int[] boxes;
    Map<Integer, TreeMap<Integer, Integer>> ranges;
    
    public int removeBoxes(int[] boxes) {
        this.dp = new int[boxes.length][boxes.length][boxes.length+1];
        this.boxes = boxes;
        this.ranges = generateRanges(boxes);
        return dfs(0, boxes.length - 1, 0);
    }
    
    private int dfs(int start, int end, int numBoxesSameColorStart) {
        if (start > end) return 0;
        if (dp[start][end][numBoxesSameColorStart] > 0) return dp[start][end][numBoxesSameColorStart];
        int num = boxes[start];
        var numTreeMap = ranges.get(num);
        
        //lets pick up the whole streak
        int streakEnd = numTreeMap.get(start);
        int numConsecutives = streakEnd - start + 1;        
        
        int ans = 0;
        //option 1 - can pick up the boxes at start - we are left but only the boxes on the right
        ans = ((numBoxesSameColorStart + numConsecutives) * (numBoxesSameColorStart + numConsecutives)) + dfs(streakEnd + 1, end, 0);
        
        //option 2- leave the boxes and extend
        for (var entry : ranges.get(num).subMap(start, false, end, true).entrySet()) {
            int interStart = entry.getKey();
            int interEnd = entry.getValue();
            //we choose to remove intervals between start and the next one
            //so we can combine the current boxes to the next one
            ans = Math.max(ans, dfs(streakEnd + 1, interStart - 1, 0) + dfs(interStart, end, (numBoxesSameColorStart + numConsecutives)));
            
        }
        
        return dp[start][end][numBoxesSameColorStart] = ans;
    }
    
    private Map<Integer, TreeMap<Integer, Integer>> generateRanges(int[] boxes) {
        Map<Integer, TreeMap<Integer, Integer>> map = new HashMap<>();
        for (int i = 0; i < boxes.length; i++) {
            int start = i;
            int boxColor = boxes[i];
            
            if (!map.containsKey(boxColor)) 
                map.put(boxColor, new TreeMap<>());
            
            while(i+1 < boxes.length && boxes[i+1] == boxColor)
                i++;
            
            map.get(boxColor).put(start, i);
        }
        
        return map;
    }
}