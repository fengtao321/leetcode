import java.util.TreeMap;

class StockPrice {
    HashMap<Integer, Integer> history;
    TreeMap<Integer, Integer> prices;
    int curr = 0;
    public StockPrice() {
        history = new HashMap<>();
        prices = new TreeMap<>();
    }
    
    public void update(int timestamp, int price) {
        if(history.containsKey(timestamp)) {
            int preP = history.get(timestamp);
            int counter = prices.get(preP) -1;
            
            if(counter == 0) {
                prices.remove(preP);
            } else {
                prices.put(preP, counter);
            }
        } 
        history.put(timestamp, price);

        prices.put(price, prices.getOrDefault(price, 0) + 1);
        curr = Math.max(curr, timestamp);
    }
    
    public int current() {
        return history.get(curr);
    }
    
    public int maximum() {
        return prices.lastKey();
    }
    
    public int minimum() {
        return prices.firstKey(); 
    }
}

/**
 * Your StockPrice object will be instantiated and called as such:
 * StockPrice obj = new StockPrice();
 * obj.update(timestamp,price);
 * int param_2 = obj.current();
 * int param_3 = obj.maximum();
 * int param_4 = obj.minimum();
 */