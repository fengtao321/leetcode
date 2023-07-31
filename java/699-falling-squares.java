class Solution {
    public List<Integer> fallingSquares(int[][] positions) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        treeMap.put(0, 0);
        int maxHeight = 0;
        int[] ans = new int[positions.length];  
        
        for (int i = 0; i < positions.length; i++) {
            int left = positions[i][0];
            int sideLength= positions[i][1];
            int right = left+sideLength;
            int height = treeMap.get(treeMap.lowerKey(right));
            int heightBeforeBlock = treeMap.get(treeMap.floorKey(left));
            
            int xCoordinate = treeMap.floorKey(right);
            while (xCoordinate>=left) {
                height = Math.max(height, treeMap.get(xCoordinate));
                treeMap.remove(xCoordinate);
                xCoordinate = treeMap.floorKey(right);  
            }
            height = height+sideLength;
            treeMap.put(left, height);
            treeMap.put(right, heightBeforeBlock);
            maxHeight = Math.max(maxHeight, height);
            ans[i] = maxHeight;
        }
        return ans;
    }
}