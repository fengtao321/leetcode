import java.util.HashMap;
import java.util.PriorityQueue;

class FreqStack {
    PriorityQueue<Node> pq;
    HashMap<Integer, Integer> map;
    int globalTime = 0;
    public FreqStack() {
        pq = new PriorityQueue<>();
        map = new HashMap<>();
    }
    
    public void push(int val) {
        int frequency = map.getOrDefault(val,0) + 1;
        Node node = new Node(val, globalTime++, frequency);
        map.put(val, frequency);
        pq.add(node);
    }
    
    public int pop() {
        int num = pq.poll().num;
        map.put(num, map.get(num)-1);
        return num;
    }
}

class Node implements Comparable<Node> {
    int num;
    int time;
    int frequency;
    public Node(int num, int time, int frequency) {
        this.num = num;
        this.time = time;
        this.frequency = frequency;
    }

    @Override
    public int compareTo(Node that) {
        if (that.frequency > this.frequency || (that.frequency == this.frequency && that.time > this.time)) {
            return 1;
        }
        return -1;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */