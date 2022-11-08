import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

class NetworkNode  implements Comparable<NetworkNode> {
    int id;
    int cost;
    public  NetworkNode(int id, int cost) {
        this.id = id;
        this.cost = cost;
    }

    public  NetworkNode(int[] time, int cost) {
        this.id = time[1];
        this.cost = time[2] + cost;
    }

    @Override
    public int compareTo(NetworkNode that){
        return this.cost - that.cost;                            // ascending order / minimum Heap
    }

}

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
         HashMap<Integer,  ArrayList<int[]>> map = new HashMap<>();  
         HashSet<Integer> nodeNotVisited = new HashSet<>();
        //  Arrays.fill(nodeNotVisited, Integer.MAX_VALUE);
        // add network to the map
        for(int[] time: times) {
            int nodeId = time[0];

            ArrayList<int[]> list = map.getOrDefault(nodeId,  new ArrayList<>());
            list.add(time);

            map.put(nodeId, list);
            nodeNotVisited.add(nodeId);
            nodeNotVisited.add(time[1]);
        }

        if(nodeNotVisited.size() < n) return -1; //at least one node is not in times matrix
         
        int minCost = 0;
        PriorityQueue<NetworkNode> visitedNodes = new PriorityQueue<>();
        visitedNodes.add(new NetworkNode(k, 0));

        while(visitedNodes.size()>0) {
            NetworkNode currNode = visitedNodes.poll();

            //check if this node is visited 
            if(!nodeNotVisited.contains(currNode.id)) {
                continue;
            }

            //remove the node id from nodeNotVisited list, and update minCost
            nodeNotVisited.remove(currNode.id);
            minCost = currNode.cost > minCost ? currNode.cost : minCost;

            //if currNode is in the map, get all the links start from currNode, and update visitedMap
            if(!map.containsKey(currNode.id)) {
                continue;
            }

            ArrayList<int[]> links = map.get(currNode.id);
            for(int[] link: links) {
                NetworkNode nextNode = new NetworkNode(link, currNode.cost);
                visitedNodes.add(nextNode);
            }
            printPriorityQueue(visitedNodes);
        }
        

    
        
        return nodeNotVisited.size()>0? -1 : minCost;
    }

    public void printPriorityQueue(PriorityQueue<NetworkNode> priorityQueue) {
        System.out.println(priorityQueue);
    }
}
