import java.util.TreeSet;

class Scene implements Comparable<Scene> {
    public String name;
    public int score;

    public Scene(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return this.name + ": " + this.score;
    }

    public int compareTo(Scene other) {
        if (this.score != other.score) {
                return this.score - other.score;
            }
        return other.name.compareTo(this.name);
    }
}

class SORTracker {
    private TreeSet<Scene> treeSet;
    private Scene prev;

    public SORTracker() {
        treeSet = new TreeSet<>();
    }
    
    public void add(String name, int score) {
        System.out.println(String.format("Adding %s %d", name, score));
        Scene scene = new Scene(name, score);
        treeSet.add(scene);
        if (prev != null && prev.compareTo(scene) < 0) {
            Scene newPrev = treeSet.higher(prev);
            // System.out.println(String.format("Shifting from %s to %s", prev, newPrev));
            prev = newPrev;
        }
    }
    
    public String get() {
        if (prev == null) {
            prev = treeSet.last();
            // System.out.println(String.format("Get1: %s", prev));
            return prev.name;
        }

        prev = treeSet.lower(prev);
        // System.out.println(String.format("Get2: %s", prev));
        return prev.name;
    }
}

/**
 * Your SORTracker object will be instantiated and called as such:
 * SORTracker obj = new SORTracker();
 * obj.add(name,score);
 * String param_2 = obj.get();
 */