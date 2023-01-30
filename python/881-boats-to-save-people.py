class Solution:
    def numRescueBoats(self, people: List[int], limit: int) -> int:
        people.sort()
        l,r, ans, counter = 0,len(people)-1, 0, 0
        
        while l <= r:
            if counter + people[r] <= limit:
                counter +=   people[r]
                r-=1
            
            if counter + people[l] <= limit:
                counter += people[l]
                l+=1
            
            ans += 1
            counter = 0
            
        return ans if counter==0 else ans+1  
        
        