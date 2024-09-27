class Solution {
public:
    bool visitNode(bool* visit, int start, vector<int>& arr, int size) {
        int step = arr[start];
        if (step == 0) {
            return true;
        }
        visit[start] = false;
        if (start+step < size && visit[start+step]) {
            if(visitNode(visit, start+step, arr, size)) {
                return true;
            }
        }
        if (start-step >= 0 && visit[start-step]) {
            if(visitNode(visit, start-step, arr, size)) {
                return true;
            }
        }
        return false;
    }
    
    bool canReach(vector<int>& arr, int start) {
        int length = arr.size();
        bool visit[length];
        memset(visit, 1, length*sizeof(bool));
        
        return visitNode(visit, start, arr, length);
    }
};