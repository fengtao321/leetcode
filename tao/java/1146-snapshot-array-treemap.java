import java.util.HashMap;
import java.util.TreeMap;

class SnapshotArray {
    HashMap<Integer, TreeMap<Integer, Integer>> changeRecord = new HashMap<>(); // index -> snap_id, value
    int snap_id = 0;

    public SnapshotArray(int length) {
        for (int i = 0; i < length; i++) {
            TreeMap<Integer, Integer> treeMap = new TreeMap<>();
            treeMap.put(snap_id, 0);
            changeRecord.put(i, treeMap);
        }
    }

    public void set(int index, int val) {
        changeRecord.get(index).put(snap_id, val);
    }

    public int snap() {
        snap_id++;
        return snap_id - 1;
    }

    public int get(int index, int snap_id) {
        TreeMap<Integer, Integer> treeMap = changeRecord.get(index);
        return treeMap.get(treeMap.floorKey(snap_id));
    }
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */