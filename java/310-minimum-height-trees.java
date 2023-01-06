import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;

import javax.swing.event.TreeExpansionEvent;

class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(n==1) 
            return new ArrayList<>(Arrays.asList(0));
        // if(n==2) 
        //     return new ArrayList<>(Arrays.asList(0, 1));

        HashMap<Integer, List<Integer>> adj = new HashMap<>();
        Set<Integer> ans = new HashSet<>();
        int[] degree = new int[n];
        for(int i =0; i<n; i++) {
            adj.put(i, new ArrayList<>());
            ans.add(i);
        }

        for(int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
            degree[u]++;
            degree[v]++;
        }

        Deque<Integer> queue = new ArrayDeque<>();
        int numProcessed = 0;
        for(int i=0; i<degree.length; i++) {
            if(degree[i]==1) {
                queue.add(i);
                numProcessed++;
            }
        }

        while(!queue.isEmpty()) {
            int size = queue.size();

            if(ans.size()<=2)
                break;
            
            for(int i =0; i<size; i++) {
                int node = queue.poll();
                ans.remove(node);
                if(adj.containsKey(node)) {
                    for(int next : adj.get(node)) {
                        degree[next]--;
                        if(degree[next]==1) {
                            queue.add(next);
                            numProcessed++;
                        }
                    }
                    adj.remove(node);
                }
            }
        }

        return List.copyOf(ans);
    }
}