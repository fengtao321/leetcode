class Solution {
public:
    Node* connect(Node* root) {
        Node* start = root;
        Node* next_start = NULL;
        Node* last = NULL;
        Node* cur;
        while(start) {
            cur = start -> left;;
            if(cur) {
                if(next_start) {
                    last -> next = cur;
                } else {
                    next_start = cur;
                }
                last = cur;
            }
            cur = start -> right;
            if(cur) {
                if(next_start) {
                    last -> next = cur;
                } else {
                    next_start = cur;
                }
                last = cur;
            }
            start = start -> next;
            if(!start) {  
                start = next_start;
                next_start = NULL;
            }
        }
        return root;
    }
};