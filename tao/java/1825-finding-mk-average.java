import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;

class MKAverage {
    Deque<Integer> queue = new ArrayDeque<>();
    // need to keep 3 stacks
    TreeMap<Integer, Integer> minStack = new TreeMap<>();
    TreeMap<Integer, Integer> maxStack = new TreeMap<>();
    TreeMap<Integer, Integer> midHeap = new TreeMap<>();

    int minStackSize = 0;
    int maxStackSize = 0;
    int sum = 0;
    int m;
    int k;

    public MKAverage(int m, int k) {
        this.m = m;
        this.k = k;
    }

    public void addElement(int num) {
        if (queue.size() == m) {
            removeFirstElement();
        }

        queue.add(num);
        addElementToStack(num, midHeap);
        sum = sum + num;

        if (queue.size() == m) {
            addElementToMinStack();
            addElementToMaxStack();
        }

    }

    public int calculateMKAverage() {
        return queue.size() < m ? -1 : (int) (sum / (m - k * 2));
    }

    private void removeFirstElement() {
        int num = queue.poll();
        if (removeElementFromStack(num, minStack)) {
            minStackSize--;
            return;
        }

        if (removeElementFromStack(num, maxStack)) {
            maxStackSize--;
            return;
        }

        sum = sum - num;
        removeElementFromStack(num, midHeap);

    }

    private void addElementToMinStack() {
        while (minStackSize < k) {
            midToMin();
        }

        while (midHeap.size() > 0 && midHeap.firstKey() < minStack.lastKey()) {
            minToMid();
            midToMin();
        }
    }

    private void addElementToMaxStack() {
        while (maxStackSize < k) {
            midToMax();
        }

        while (midHeap.lastKey() > maxStack.firstKey()) {
            maxToMid();
            midToMax();
        }
    }

    private void minToMid() {
        int ele = minStack.lastKey();
        removeElementFromStack(ele, minStack);
        sum = sum + ele;
        addElementToStack(ele, midHeap);
        minStackSize--;
    }

    private void midToMin() {
        int ele = midHeap.firstKey();
        removeElementFromStack(ele, midHeap);
        sum = sum - ele;
        addElementToStack(ele, minStack);
        minStackSize++;
    }

    private void maxToMid() {
        int ele = maxStack.firstKey();
        removeElementFromStack(ele, maxStack);
        sum = sum + ele;
        addElementToStack(ele, midHeap);
        maxStackSize--;
    }

    private void midToMax() {
        int ele = midHeap.lastKey();
        removeElementFromStack(ele, midHeap);
        sum = sum - ele;
        addElementToStack(ele, maxStack);
        maxStackSize++;
    }

    private void addElementToStack(int num, TreeMap<Integer, Integer> stack) {
        stack.put(num, stack.getOrDefault(num, 0) + 1);
    }

    private boolean removeElementFromStack(int num, TreeMap<Integer, Integer> stack) {
        if (!stack.containsKey(num)) {
            return false;
        }

        int counter = stack.get(num) - 1;
        if (counter == 0) {
            stack.remove(num);
        } else {
            stack.put(num, counter);
        }

        return true;
    }
}