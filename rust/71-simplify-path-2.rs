impl Solution {
    pub fn simplify_path(path: String) -> String {
        let segments: Vec<_> = path.split('/').collect();
        let mut path_stack = vec![];
        for seg in segments {
            if seg.is_empty() || seg == "." {
                continue;
            }

            if seg == ".." {
                path_stack.pop();
                continue;
            }

            path_stack.push(seg);
        }

        let mut result = String::new();
        result.push("/");
        result.push_str(&path_stack.join("/"));
        return result;
    }
}