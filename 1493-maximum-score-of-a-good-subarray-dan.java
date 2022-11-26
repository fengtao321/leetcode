// can check this for more info https://www.youtube.com/watch?v=0jWeUdxrGm4

class SparseTable {

    // we'll build a sparse table to calculate the min range in 0(1)
    int[][] table;
    int LOG;

    private void preprocess(int[] arr) {
        this.LOG = Integer.numberOfTrailingZeros(Integer.highestOneBit(arr.length)) + 1;
        this.table = new int[arr.length][this.LOG];

        for (int i = 0; i < arr.length; i++) {
            // if the range is 2^0 then the min is the initial value
            this.table[i][0] = arr[i];
        }

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i + (1 << k) - 1 < arr.length; i++) {
                // let's say you want to calculate for i = 0 and k = 3
                // this means we want to know the min value from index i of length 2^3 (so
                // length of 8)
                // for this, we simply use the value we already calculated previously
                // min of:
                // 1) value of length 2^2 at index 0
                // 2) value of length 2^2 at index 4
                // finally, the condition "i + (1 << k) - 1" just means we stop when there is
                // not enough value after i of length 2^k
                this.table[i][k] = Math.min(this.table[i][k - 1], this.table[i + (1 << (k - 1))][k - 1]);
            }
        }
    }

    /**
     * find the minimum value of a range in 0(1)
     *
     * For example, let's say the left == 2 and right == 11. We have a length of 10
     * We need to find the power of 2 that is smaller or equal to 10.
     * In this case it's 2^3. (so k == 3)
     * So we need to lookup 2 values
     * 1) the min value at index 2, for k == 3
     * 2) the min value at index 4 for k == 3 ( 11 - 2^3 + 1)
     * 
     * They key concept is by looking at 2 values of length k, we can know the min
     * of the range.
     *
     * @param left  left bound of the interval
     * @param right right bound of the interval
     * @return the minimum value of the range
     */
    private int query(int left, int right) {
        int length = right - left + 1;
        int k = Integer.numberOfTrailingZeros(Integer.highestOneBit(length));
        return Math.min(this.table[left][k], this.table[right - (1 << k) + 1][k]);
    }

    // public static void main( String[] args ) {
    // int[] arr = new int[ ] {1,2,3,4,5,6,7,8,9,10,11,12,13};

    // SparseTable sparseTable = new SparseTable();
    // sparseTable.preprocess(arr);

    // System.out.println(sparseTable.query(0, 12));//1
    // System.out.println(sparseTable.query(1, 12));//2
    // System.out.println(sparseTable.query(2, 12));//3
    // System.out.println(sparseTable.query(3, 12));//4
    // System.out.println(sparseTable.query(4, 12));//5
    // System.out.println(sparseTable.query(5, 12));//6
    // System.out.println(sparseTable.query(2, 7));//3
    // System.out.println(sparseTable.query(1, 3));//2
    // System.out.println(sparseTable.query(4, 10));//5
    // System.out.println(sparseTable.query(6, 11));//7
    // System.out.println(sparseTable.query(2, 2));//3
    // }
}
