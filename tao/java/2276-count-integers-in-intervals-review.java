import java.util.TreeMap;

public class CountIntervals {
    TreeMap<Integer, Integer> treemap;
    int counter = 0;
    public CountIntervals() {
        treemap = new TreeMap<>();
        treemap.put(0, 0);
    }
    
    public void add(int left, int right) {
        right = right + 1;
        int floorKey = treemap.floorKey(right);

        int curr = 0;
        if( treemap.get(floorKey) > 0) {
            curr += right;
        } 
        while(floorKey>=left) {
            if(treemap.get(floorKey)>0) {
                curr -=  floorKey;
            } else {
                curr +=  floorKey;
            }
            treemap.remove(floorKey);
            floorKey = treemap.floorKey(right);
        }

        Integer higherKey = treemap.higherKey(right);
        if(higherKey == null || treemap.get(higherKey)>0) {
            treemap.put(right, treemap.getOrDefault(right, 0));
        }

        if( treemap.get(floorKey) > 0) {
            curr -= left;
        }  else {
            treemap.put(left, 1);
        }
        
        counter = counter + right - left - curr;
        // printTreeMap(treemap);

    }
    
    public int count() {
        return counter;
    }

    private void printTreeMap(TreeMap<Integer, Integer> sourceMap) {
        System.out.println("==============");
        sourceMap.forEach((k, v) -> {
            System.out.print("key: " + k + " value: " + v);
            System.out.println("");
        });
    }
}

/**
 * Your CountIntervals object will be instantiated and called as such:
 * CountIntervals obj = new CountIntervals();
 * obj.add(left,right);
 * int param_2 = obj.count();
 */