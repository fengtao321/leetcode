class Solution:
    def interchangeableRectangles(self, rectangles: List[List[int]]) -> int:
        hashmap = defaultdict(int)
        ans = 0
        for rectangle in rectangles:
            key = rectangle[0]/rectangle[1]
            ans+=hashmap[key]
            hashmap[key]+=1

        return ans            