from collections import defaultdict


class Solution:
    def minimumTime(self, n: int, relations: List[List[int]], time: List[int]) -> int:
        dict = defaultdict(list)
        courses = [0] * (n+1)
        dp =  [0] * (n+1)
        
        for relation in relations:
            dict[relation[0]].append(relation[1])
            courses[relation[1]] += 1
        
        queue = []
        for i in range(1, n+1):
            if courses[i] == 0:
                queue.append(i)
                dp[i] = time[i-1]
        
        while(queue):
            l = len(queue)
            for i in range(l):
                course = queue.pop(0)         
                for nextCourse in dict[course]:
                    courses[nextCourse] -= 1
                    dp[nextCourse] = max(dp[nextCourse] , dp[course] + time[nextCourse-1])
                    if courses[nextCourse] == 0:
                        queue.append(nextCourse)
                del dict[course]
        return max(dp)