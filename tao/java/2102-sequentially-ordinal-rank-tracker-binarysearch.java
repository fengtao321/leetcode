import java.util.List;

class SORTracker {
    TreeMap<Integer, List<String>> map;
    int counter = 0;
    public SORTracker() {
        map = new TreeMap<>(Collections.reverseOrder());
    }
    
    public void add(String name, int score) {
        if (!map.containsKey(score)) {
            map.put(score, new ArrayList<>());
        }
        List<String> list = map.get(score);
        binarySearch(list, name, 0, list.size()-1);
    }
    
    public String get() {
        counter++;
        int curr = 0;
        for(int score: map.keySet()) {
            List<String> list = map.get(score);
            if(curr + list.size()>=counter) {
               return list.get(counter-curr-1); 
            }
            curr += list.size();
        }
        return "";
    }

    private void binarySearch(List<String> list, String name, int left, int right) {
        if(left > right) {
            list.add(left, name);
            return;
        }

        int mid = (int)((left + right) /2);
        String midName = list.get(mid);
        if(midName.compareTo(name) < 0) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
        binarySearch(list, name, left, right); 
    }
}



/**
 * Your SORTracker object will be instantiated and called as such:
 * SORTracker obj = new SORTracker();
 * obj.add(name,score);
 * String param_2 = obj.get();
 */