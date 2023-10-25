use std::collections::HashMap;
use std::cmp::min;
use std::str::Chars;

impl Solution {
    pub fn min_distance(word1: String, word2: String) -> i32 {
        let M = word1.len();
        let N = word2.len();
        let word1_char: Vec<char> = word1.chars().collect();
        let word2_char: Vec<char> = word2.chars().collect();
        
        let mut dp: HashMap<(i32, i32), i32> = HashMap::new();
        
        fn dfs(i: i32, j:i32, M: i32, N:i32, word1_char: &Vec<char>, word2_char: &Vec<char>, dp: &mut HashMap<(i32, i32), i32>) -> i32 {
            if i == M {return N-j;} 
            if j == N {return M-i;}
            
            match dp.get(&(i, j)) {
                Some(&counter) => return counter,
                None => {
                    let mut curr = 0;
                    if word1_char[i as usize] == word2_char[j as usize] { 

                        curr = dfs(i+1, j+1, M,N, word1_char, word2_char, dp);
                    } 
                    else {
                        let insert = dfs(i, j+1,  M, N,  word1_char,  word2_char, dp);
                        let delete = dfs(i+1, j,  M, N,  word1_char,  word2_char, dp);
                        let replace = dfs(i+1, j+1,  M, N,  word1_char,  word2_char, dp);
                        curr = 1 + min(min(insert, delete), replace);
                    }
                    dp.insert((i, j), curr);
                    return curr;
                }
            }
        }
        
        return dfs(0, 0, M as i32, N as i32,  &word1_char,  &word2_char, &mut dp);
    }
}