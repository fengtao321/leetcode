class Solution {
public:
    vector<int> findMaxProfitInArea(int leftbound, int rightbound, vector<int>& prices) {
        vector<int> vec;
        if (rightbound-leftbound <= 0) {
            return vec;
        }
        if (leftbound < 0 || rightbound > prices.size()-1) {
            return vec;
        }
        int last = 0;
        int max = 0;
        int tmpLeft = leftbound;
        int tmpRight = rightbound;
        for (int i = leftbound+1; i <= rightbound; i++) {
            last = last + prices[i]-prices[i-1];
            if (prices[i] > prices[i-1]) {
                if (last > max) {
                    max = last;
                    tmpRight = i;
                } 
            } else if (last < 0) {
                last = 0;
            }
        }
        int min = prices[tmpRight];
        for (int i = leftbound; i < tmpRight; i++) {
            if (prices[i] < min) {
                min = prices[i];
                tmpLeft = i;
            }
        }
        if (max <= 0) {
            return vec;
        }
        
        vec.push_back(leftbound);
        vec.push_back(tmpLeft);
        vec.push_back(tmpRight);
        vec.push_back(rightbound);
        vec.push_back(0);
        vec.push_back(max);
        return vec;
    }
    
    vector<int> findMaxRevProfitInArea(int leftbound, int rightbound, vector<int>& prices) {
        vector<int> vec;
        if (rightbound-leftbound <= 0) {
            return vec;
        }
        if (leftbound < 0 || rightbound > prices.size()-1) {
            return vec;
        }
        int last = 0;
        int max = 0;
        int tmpLeft = leftbound;
        int tmpRight = leftbound+1;
        for (int i = leftbound+1; i <= rightbound; i++) {
            last = last - prices[i]+prices[i-1];
            if (prices[i] < prices[i-1]) {
                if (last > max) {
                    max = last;
                    tmpRight = i;
                } 
            } else if (last < 0) {
                last = 0;
            }
        }
        int min = prices[tmpRight];
        for (int i = leftbound; i < tmpRight; i++) {
            if (prices[i] > min) {
                min = prices[i];
                tmpLeft = i;
            }
        }
        if (max <= 0) {
            return vec;
        }
        vec.push_back(leftbound);
        vec.push_back(tmpLeft+1);
        vec.push_back(tmpRight-1);
        vec.push_back(rightbound);
        vec.push_back(1);
        vec.push_back(max);
        return vec;
        
    }
    
    int findMax(vector<vector<int>>& max) {
        int temp = max[0][5];
        int index = 0;
        int i=0;
        for (vector<vector<int>>::iterator iter = max.begin(); iter != max.end(); iter++, i++) {
            if (temp < (*iter)[5]) {
                temp = (*iter)[5];
                index = i;
            }
        }
        return index;
    }
    
    int maxProfit(int k, vector<int>& prices) {
        int count = 0;
        int sum = 0;
        vector<vector<int>> groups;
        
        if (prices.size() < 2) {
            return 0;
        }
        
        vector<int> temp = findMaxProfitInArea(0, prices.size()-1, prices);
        if (temp.size() > 0) {
            groups.push_back(temp);
        }
        
        while (count < k && groups.size() > 0) {
            
            int index = findMax(groups);        
            temp = groups[index];
            
            int ll = temp[0];
            int lr = temp[1];
            int rl = temp[2];
            int rr = temp[3];
            sum += temp[5];
            groups.erase(groups.begin()+index);
            
            vector<int> temp1;
            vector<int> temp2;
            vector<int> temp3;
            
            if (temp[4] == 1) {
                // Gap from existing
                temp1 = findMaxRevProfitInArea(ll, lr-1, prices);
                temp2 = findMaxProfitInArea(lr, rl, prices);
                temp3 = findMaxRevProfitInArea(rl+1, rr, prices);
            } else {
                // Gap from none
                temp1 = findMaxProfitInArea(ll, lr-1, prices);
                temp2 = findMaxRevProfitInArea(lr, rl, prices);
                temp3 = findMaxProfitInArea(rl+1, rr, prices);
            }
            
            if (temp1.size() != 0) {
                groups.push_back(temp1);
            }
            if (temp2.size() != 0) {
                groups.push_back(temp2);
            }
            if (temp3.size() != 0) {
                groups.push_back(temp3);
            }
            
            count++;
        }
        return sum;
    }
};