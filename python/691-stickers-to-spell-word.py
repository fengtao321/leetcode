class Solution:
    def minStickers(self, stickers: List[str], target: str) -> int:
        N, target_set, record = len(target), set(target), []
        target_binary = 2**N-1
        
        for stick in stickers:
            hashmap = defaultdict(int)
            
            for c in stick:
                hashmap[c]+=1
                if c in target_set: target_set.remove(c)
            record.append(hashmap)
            
        if len(target_set)>0: return -1
        
        ans, visited, queue = 0, {0}, {0}
        
        while queue:
            next_queue = set()
            for x in queue:
                if x==target_binary: return ans
                if x>target_binary: continue
                
                for hm in record:
                    hash_map = hm.copy()
                    z = x
                    for i in range(N):
                        if (1<<i)&z==0 and target[i] in hash_map and hash_map[target[i]]>0:
                            z = z^(1<<i)
                            hash_map[target[i]]-=1

                    if z not in visited:
                        next_queue.add(z)
                        visited.add(z)
            queue = next_queue
            ans+=1

        return -1