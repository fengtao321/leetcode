class Codec:
    def __init__(self) -> None:
        self.base_url = "http://tinyurl.com/"
        self.encode_map = {}    
        self.decode_map = {}    
    
    def encode(self, longUrl: str) -> str:
        """Encodes a URL to a shortened URL.
        """
        if longUrl not in self.encode_map:
            shortUrl = self.base_url + str(len(self.encode_map) + 1)
            self.decode_map[shortUrl] = longUrl
            self.encode_map[longUrl] = shortUrl
        
        return self.encode_map[longUrl]
        
        

    def decode(self, shortUrl: str) -> str:
        """Decodes a shortened URL to its original URL.
        """
        return self.decode_map[shortUrl]

# Your Codec object will be instantiated and called as such:
# codec = Codec()
# codec.decode(codec.encode(url))