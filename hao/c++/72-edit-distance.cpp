class Solution {
public:
    int min(int a,int b,int c) {
        
        if (a < b) {
            if (a < c) {
                return a;
            } else {
                return c;
            }
        } else {
            if (b < c) {
                return b;
            } else {
                return c;
            }
        }
        return a;
    }
    
    int minDistance(string word1, string word2) {
        /* DP[i][j] : min distance with words word1[i,end] and word2[j,end]
        if word1[i] != word2[j]:
        DP[i,j] = dp[i+1,j+1]+1 replace
        dp[i,j] = dp[i,j+1]+1 insert
        dp[i,j] = dp[i+1,j]+1 delete
        
        word1: m
        word2: n
        
        Start:
        dp[i,n-1] = m-i;
        dp[m-1,j] = n-j;
        
        */
        
        int m = word1.size();
        int n = word2.size();
        
        if (m==0) {
            return n;
        }
        if (n==0) {
            return m;
        }
        
        int* dp = (int*)malloc((m+1)*(n+1)*sizeof(int));
        
        for (int i = n; i >= 0; i--) {
            dp[m*n+m+n-i] = i;
            //dp[m,i] = n-i
            //printf("Assign IJ: %d %d\n",m*n+m-i, dp[m*n+m+n-i]);
        }
        for (int i = 0; i <= m; i++) {
            //dp[i,n] = m-i
            dp[i*n+i+n]=m-i;
            //printf("Assign IJ: %d %d\n",i*(n+1)+n, dp[i*(n+1)+n]);
        }
        for (int i = m-1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {
                if (word1[i] == word2[j]) {
                    dp[i*n+i+j] = dp[i*n+i+n+j+2];
                    //dp[i*(n+1)+j] = dp[(i+1)*(n+1)+j+1];
                    //dp[i,j]=dp[i+1,j+1]
                }
                else {
                    dp[i*n+i+j] = min(dp[i*n+i+n+j+2], dp[i*n+i+j+1], dp[i*n+i+1+n+j]) + 1;
                    //printf("MIN: %d %d %d\n", i*n+i+n+j+2,i*n+i+j+1,i*n+i+1+n+j);
                    //dp[i*(n+1)+j] = min(dp[(i+1)*(n+1)+(j+1)], dp[i*(n+1)+j+1], dp[(i+1)*(n+1)+j])+1
                    //dp[i,j]=min(dp[i+1,j+1], dp[i,j+1], dp[i+1,j])+1
                }
                
            }
        }
        int temp = (int)dp[0];
        free(dp);
        return temp;
    }
};