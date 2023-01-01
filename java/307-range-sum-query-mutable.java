class NumArray {
    int[] tree;
    int[] nums;
    int numsN;
    int treeN;

    public NumArray(int[] nums) {
        this.nums = nums;
        this.numsN = nums.length;
        this.treeN = 2 * this.numsN;
        this.tree = new int[this.treeN];
        this.tree[0] = 0; // dummy node, won't be used

        for (int i = 0; i < this.numsN; i++) {
            this.tree[i + this.numsN] = nums[i]; // will be [0, null, null, null, 4, 6, 7, 8]
        }

        for (int i = this.numsN - 1; i > 0; i--) {
            int j = i << 1;
            this.tree[i] = this.tree[j] + this.tree[j + 1];
        }
    }

    public void update(int index, int val) {
        int diff = val - this.nums[index];
        this.nums[index] = val;

        index = index + this.numsN;
        while (index > 0) {
            this.tree[index] = this.tree[index] + diff;
            index = index >> 1;
        }
    }

    public int sumRange(int left, int right) {
        return sumRangeHelp(left + this.numsN, right + this.numsN);
    }

    private int sumRangeHelp(int left, int right) {
        if (left == right)
            return this.tree[left];

        int ans = 0;
        if (left % 2 == 1) {
            ans = ans + this.tree[left];
            left = (left + 1) >> 1;
        }

        if (right % 2 == 1) {
            ans = ans + this.tree[right - 1];
            right = (right - 1) >> 1;
        }

        return ans + this.sumRangeHelp(left, right);

    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */