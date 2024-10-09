class LockingTree:

    def __init__(self, parent: List[int]):
        n = len(parent)
        self.children_map, self.parent_map = defaultdict(set),defaultdict(set)
        self.locked_set, self.lock_user = set(), [-1]*n
        # self.parent_map[0].add(-1)
        for i in range(1, n):
            self.children_map[parent[i]].add(i)

        q = deque([0])
        while q:
            size = len(q)
            for _ in range(size):
                p = q.popleft()
                children = self.children_map[p]
                for child in children:
                    q.append(child)
                    self.parent_map[child].add(p)
                    self.parent_map[child] = self.parent_map[child].union(self.parent_map[p])
        
        for child in self.parent_map:
            for p in self.parent_map[child]:
                self.children_map[p].add(child)
                
        
    def lock(self, num: int, user: int) -> bool:
        
        if num in self.locked_set: return False
        self.locked_set.add(num)
        self.lock_user[num] = user
        return True

    def unlock(self, num: int, user: int) -> bool:
        if num not in self.locked_set or self.lock_user[num]!=user: return False
        
        self.locked_set.remove(num)
        self.lock_user[num] = -1
        return True
        
    def upgrade(self, num: int, user: int) -> bool:
        # print(self.locked_set)
        if num in self.locked_set or self.parent_map[num].intersection(self.locked_set): return False
        children = self.children_map[num].intersection(self.locked_set)
        if not children: return False
        
        self.locked_set.add(num)
        self.lock_user[num] = user
        for child in children:
            self.locked_set.remove(child)
            self.lock_user[child] = -1
        return True
                
        
        


# Your LockingTree object will be instantiated and called as such:
# obj = LockingTree(parent)
# param_1 = obj.lock(num,user)
# param_2 = obj.unlock(num,user)
# param_3 = obj.upgrade(num,user)

export const state = () => ({    
                             sessionExpired: false,    
                             customerNumber: null,    auth: {        tenantId: process.env.AZURE_TENANT_ID,        clientId: process.env.AZURE_CLIENT_ID,        authority: process.env.AZURE_AUTHORIZATION,        redirectUri: process.env.AZURE_REDIRECT_URL,        postLogoutRedirectUri: process.env.AZURE_REDIRECT_URL,        autoRefreshToken: true    },    cache: {        cacheLocation: 'localStorage', // This configures where your cache will be stored        storeAuthStateInCookie: true // Set this to "true" if you are having issues on IE11 or Edge    // secureCookies: true    },    request: {        scopes: ['openid', 'profile', 'email']    },    graph: {        callAfterInit: true    },    token: null});