class TrieNode:
     def __init__(self):
         self.children = {}
         self.value = ""
    
class WordDictionary:
    
    def __init__(self):
        self.hashMap = {}

    def addWord(self, word: str) -> None:
        curr = self.hashMap
        for char in word:
            if char not in curr:
                curr[char] = {}
            curr = curr[char]
                
        curr["#"] = "#"
        
    def search(self, word: str) -> bool:        
        def dfs(i, curr):
            if i == len(word):
                return curr["#"]=="#"
            
            if word[i] in curr:
                return dfs(i+1, curr[word[i]])
            
            if word[i]=='.':
                for char in curr:
                    if dfs(i+1, curr[char]):
                        return True
            
            return False
            
        
        return dfs(0, self.hashMap)
    
            
        


# Your WordDictionary object will be instantiated and called as such:
# obj = WordDictionary()
# obj.addWord(word)
# param_2 = obj.search(word)