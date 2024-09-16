// class Solution {
// public:
//     int findMinDifference(vector<string>& timePoints) {
//         bool[24][60] bits = {false};
//         uint8_t hour;
//         uint8_t minute;
//         string time;
//         int min;

//         uint8_t last_min;
        
//         for(unsigned short i = 0; i < timePoints.size(); i++) {
//             time = timePoints[i];
//             hour = time[0] * 10 + time[1] - 528;
//             minute = time[3] * 10 + time[4] - 528;
//             cout << hour << ":" << minute << endl;
//             if(bits[hour][minute]) {
//                 return 0;
//             } else {
//                 bits[hour][minute] = true;
//             }
//         }

//         for(unsigned short i = 0; i < 24; i++) {
//             uint8_t last_min = 0;
//             for(unsigned short j = 0; j < 60; j++) {

//             }
//         }
//     }
// };

class Solution {
public:
    int findMinDifference(vector<string>& timePoints) {
        vector<unsigned short> time;
        string timeStr;
        unsigned short rec;
        unsigned short last_rec = -1;
        unsigned short min = 1440;
        unsigned short i;
        if(timePoints.size() > 1440) {
            return 0;
        }
        for (i = 0; i < timePoints.size(); i++) {
            timeStr = timePoints[i];
            rec = timeStr[0] * 600 + timeStr[1] * 60 + timeStr[3] * 10 + timeStr[4] - 32208;
            time.push_back(rec);
            cout << rec << endl;
        }

        sort(time.begin(), time.end());      
        for (i = 1; i < timePoints.size(); i++) {
            if (time[i] - time[i-1] < min) {
                min = time[i] - time[i-1];
            }
        }
        
        if (time[0] + 1440 - time[i-1] < min) {
            min = time[0] + 1440 - time[i-1];
        }
        return min;
    }
};