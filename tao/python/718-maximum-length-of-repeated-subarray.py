class Solution:
    def findLength(self, nums1: List[int], nums2: List[int]) -> int:
        def found(limit):
            list1 = rollingHash(limit, nums1)
            list2 = rollingHash(limit, nums2)
            return len(list1.intersection(list2)) > 0
            
            
        def rollingHash(limit, nums):
            print("limit = ", limit)
            print(nums)
            p, m = 101, 10**9+7
            hash_so_far , b = 0,1
            list = set()
            
            for i in range(limit-1):
                b =(b * p) % m
            
            for i in range(limit):
                hash_so_far = (hash_so_far * p + nums[i] ) % m
                
            list.add(hash_so_far)    
                
            for i in range(limit, len(nums)):
                hash_so_far = ((hash_so_far - nums[i-limit] * b) * p + nums[i]) % m
                list.add(hash_so_far)
            print(list)
            return list
        
        ans, left, right = 0, 0, min(len(nums1), len(nums2))
        
        while left < right: 
            mid = (left + right ) // 2
            if found(mid):
                ans = max(ans,mid)
                left = mid + 1
            else:
                right = mid -1
        return ans
            
                



# Let’s take the array 1,2,3,1,2,3,4 and we want to find all substrings of length 4
# The first hash (index 0 to 3 will be (assuming a base of 10)
#     firstHash = (10^3 * 1) + (10^2 * 2) + (10^1 * 3) + (10^0 * 1)

# Now for all the other hashes
#     //remove the number just outside the window
#     Hash = firstHash - (10^3 * 1)
#     //multiply by the base
#     Hash *= 10
# This means
#     (10^2 * 2) + (10^1 * 3) + (10^0 * 1)
# Will become
#     (10^3 * 2) + (10^2 * 3) + (10^1 * 1)
# Finally add the new number that is freshly included in the window
# Hash += 2
# and now you have a new hash for the new window!

# private Set<Long> generateHashes(int[] arr, int windowSize, int base) {
#         Set<Long> set = new HashSet();
        
#         // calculate hash of first window
#         long winHash = 0L;
#         for (var i = 0; i < windowSize; i++)
#             winHash = (base * winHash + arr[i]) % MOD;
#         set.add(winHash);
#         var E = 1L;
#         for (var i = 1; i < windowSize; i++)
#             E = base * E % MOD;
#         for (int i = 1; i <= arr.length - windowSize; i++) {
#             // remove first char
#             winHash = (winHash - E * arr[i - 1] % MOD + MOD) % MOD;
#             // left shift
#             winHash = winHash * base % MOD;
#             // add new last char
#             winHash = (winHash + arr[i + windowSize - 1]) % MOD;
            
#             set.add(winHash);
#         }
#         return set;
#     }
# 4:55
# this is polynomial hash or rolling hash