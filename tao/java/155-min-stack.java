import java.util.ArrayDeque;
import java.util.Deque;

class MinStack {
    Deque<Integer> vDeque;
    Deque<Integer> mDeque;

    public MinStack() {
        vDeque = new ArrayDeque<>();
        mDeque = new ArrayDeque<>();
    }

    public void push(int val) {
        vDeque.add(val);
        Integer min = mDeque.peekLast();
        mDeque.add(min == null ? val : Math.min(min, val));
    }

    public void pop() {
        vDeque.pollLast();
        mDeque.pollLast();
    }

    public int top() {
        return vDeque.peekLast();
    }

    public int getMin() {
        return mDeque.peekLast();
    }
}
