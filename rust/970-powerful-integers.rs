use std::collections::HashSet;

pub fn powerful_integers(x: i32, y: i32, bound: i32) -> Vec<i32> {
    fn help(num: i32, bound: i32) -> i32 {
        if num==1 {
            return 1;
        }
        let mut curr = 1;
        let mut index = 0;
        while curr <= bound {
            index += 1;
            curr *= num;
        }
        return index;
    }
    
    if bound<2 {
        return Vec::new();
    }

    let index_x:i32= help(x, bound);
    let index_y:i32 = help(y, bound);

    let mut ans: HashSet<i32> = HashSet::new();
    let mut curr_x = 1;

    for _ in 0..index_x {
        let mut curr_y = 1;
        for _ in 0..index_y {
            if curr_x + curr_y > bound {continue;}
            ans.insert(curr_x + curr_y);
            curr_y *= y;
            
        }
        curr_x*=x;
    }
    return ans.into_iter().collect();
}


fn main() {
   let ans:Vec<_> = powerful_integers(2, 3, 10);
   print!("{:?}", ans);
}