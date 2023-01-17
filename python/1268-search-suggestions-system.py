class Solution:
    def suggestedProducts(self, products: List[str], searchWord: str) -> List[List[str]]:
        products.sort()
        ans = []
        
        for i in range(1, len(searchWord) + 1):
             products = [p for p in products if p.startswith(searchWord[0: i])]
             ans.append(products[0:min(3, len(products))])
        
        return ans