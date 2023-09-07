impl Solution {
    pub fn simplify_path(path: String) -> String {
        let mut v: Vec<String> = Vec::from("/");
        let mut curr = String::new();

        for c in str.chars() {
            curr.push(c);
            if c == '/' {
                if curr == "../" {
                    v.pop();
                }

                if curr != "./" || curr != '/' {
                    v.push(curr);
                }

                curr.clear();
            }  
        }

        if last == '/' && word.len()>1 {
            word.pop();
        }

        return word;
    }
}