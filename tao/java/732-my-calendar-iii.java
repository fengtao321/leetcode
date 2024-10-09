class MyCalendarThree {
    TreeMap<Integer, Integer> map;
    int max = 0;
    public MyCalendarThree() {
        map = new TreeMap<>();
        map.put(0,0);
    }
    
    public int book(int startTime, int endTime) {
        map.put(endTime, map.get(map.floorKey(endTime)));
        map.put(startTime, map.get(map.floorKey(startTime)) + 1);
        max = Math.max(max,map.get(startTime));

        for(Integer time : map.keySet())	{
            if(time<=startTime)  continue;
            if (time >= endTime) {
                break;
            }

            map.put(time, map.get(time)+1);
            max = Math.max(max, map.get(time));
        }    
        return max;
    }
}

/**
 * Your MyCalendarThree object will be instantiated and called as such:
 * MyCalendarThree obj = new MyCalendarThree();
 * int param_1 = obj.book(startTime,endTime);
 */

/**
 * Your MyCalendarThree object will be instantiated and called as such:
 * MyCalendarThree obj = new MyCalendarThree();
 * int param_1 = obj.book(startTime,endTime);
 */

/**
 * Your MyCalendarThree object will be instantiated and called as such:
 * MyCalendarThree obj = new MyCalendarThree();
 * int param_1 = obj.book(startTime,endTime);
 */