class Solution:
    def countVowelStrings(self, n: int) -> int:
        base = {"a":1, "e":1, "i":1, "o":1, "u":1}
        for i in range(1, n):
            base["a"] = sum(base.values())
            base["e"] = sum([base["e"],base["i"],base["o"],base["u"]])
            base["i"] = sum([base["i"],base["o"],base["u"]])
            base["o"] = sum([base["o"],base["u"]])
        return sum(base.values())   
        