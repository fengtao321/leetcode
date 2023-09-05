
pub fn sort_colors(nums: &mut Vec<i32>) {
    let high = nums.len() - 1;
    quicksort(nums, 0, high);
    print!("{:?}", &nums);
}

fn quicksort(nums: &mut Vec<i32>, low: usize, high: usize) {
    if low < high {
       let  pi = partition(nums, low, high);
        quicksort(nums, low, pi-1);
        quicksort(nums, pi+1, high);
    }
}

fn partition(nums: &mut Vec<i32>, low: usize, high: usize) -> usize  {
        let pivot = *nums.get(high).unwrap();
        let mut i: usize = low - 1;

        for j in low..high {
            if nums[j] < pivot {
                i+=1;
                nums.swap(i, j);
            }
        }

        i+=1;
        nums.swap(i, high);
        return i+1;
}



fn main() {
    let mut nums:Vec<i32> = vec![2,0,2,1,1,0];
    sort_colors(&mut nums);
}
