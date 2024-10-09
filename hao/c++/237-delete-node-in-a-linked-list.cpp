/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    void deleteNode(ListNode* node) {
        memcpy(node, node->next, sizeof(ListNode));
        // ListNode* next = node->next;
        // node->val = next->val;
        // node->next = next->next;
    }
};