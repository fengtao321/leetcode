class Solution:
    def findOrder(self, numCourses: int, prerequisites: List[List[int]]) -> List[int]:
        dependencies = [0] * numCourses
        hashMap = {}
        
        for prerequisite in prerequisites:
            hashMap.setdefault(prerequisite[1], []).append(prerequisite[0])
            dependencies[prerequisite[0]] += 1
        
        queue = []
        for i in range(len(dependencies)):
            if dependencies[i] == 0:
                queue.append(i)

        index = 0
        while index < len(queue) and hashMap:
            if queue[index] in hashMap:
                for i in hashMap[queue[index]]:
                    if dependencies[i] == 1:
                        queue.append(i)
                    else:
                        dependencies[i] -=1 
                del hashMap[queue[index]]
            index +=1
        return queue if len(queue) == numCourses else [] 
        