def solution(room_dimensions, your_position, trainer_position, shot_distance ):
    def isWithinMaxDistance(x1, y1, x2, y2, shot_distance):
        return (x1-x2)**2 + (y1-y2)**2 <= shot_distance**2
    
    def get_next_position(phase):
        r_x, r_y = phase
        e_x, e_y = trainer_position
        dim_x, dim_y = room_dimensions
        res_x = dim_x*r_x + e_x if r_x % 2 == 0 else dim_x*r_x + (dim_x - e_x)
        res_y = dim_y*r_y + e_y if r_y % 2 == 0 else dim_y*r_y + (dim_y - e_y)

        return res_x, res_y
    
    def getVector(x_des, y_des, x_org, y_org):
        x, y = x_des - x_org, y_des - y_org
        if x==0 and y==0:
            return (0, 0)
        elif x == 0:
            return (0, y//abs(y))
        elif y == 0:
            return (x//abs(x), 0)
        else:
            div  = gcd(abs(x), abs(y))
            return (x//div, y//div)
    
    def passYourPosition(x_des, y_des, tuple):
        return getVector(x_des, y_des, trainer_position[0], trainer_position[1]) == tuple
           
    def calculate(phase):
        x_des, y_des = get_next_position(phase)
        curr = isWithinMaxDistance(your_position[0], your_position[1], x_des, y_des, shot_distance)
        if curr == True:
            tuple = getVector(x_des, y_des, your_position[0], your_position[1])
            if tuple in visited or passYourPosition(x_des, y_des, tuple):
                curr = False
            else:
                visited.add(tuple)
        return curr 
            
        
    def gcd(a, b):
        return b if a ==0  else gcd(b % a, a)
    
    num_rooms_above_x_axis = (shot_distance + your_position[1] )//room_dimensions[1] + 1
    num_rooms_below_x_axis = -((shot_distance - your_position[1] )//room_dimensions[1] + 1)
    num_rooms_left_of_y_axis = -((shot_distance - your_position[0])//room_dimensions[0] + 1)
    num_rooms_right_of_y_axis = (shot_distance + your_position[0])//room_dimensions[0] + 1
    
    visited, ans = set(), 0
    for i in range(num_rooms_left_of_y_axis, num_rooms_right_of_y_axis):
        for j in range(num_rooms_below_x_axis, num_rooms_above_x_axis):
            if calculate([i, j]): ans+=1
    visited = list(visited)
    visited.sort()
    # print(visited)
    return ans

def main():
    print(solution([3, 2], [1, 1], [2, 1], 4))
    print(solution([2, 5], [1, 2], [1, 4], 11))
    # print(solution([23, 10], [6, 4], [3, 2], 23))
    # print(solution([1250, 1250], [1000, 1000], [500, 400], 10000))
    # assert solution([3, 2], [1, 1], [2, 1], 4) == 7
    # assert solution([2, 5], [1, 2], [1, 4], 11) == 27
    # assert solution([23, 10], [6, 4], [3, 2], 23) == 8
    # # assert solution([1250, 1250], [1000, 1000], [500, 400], 10000) == 196
    # assert solution([10, 10], [4, 4], [3, 3], 5000) == 739323
    # assert solution([3, 2], [1, 1], [2, 1], 7) == 19
    # assert solution([2, 3], [1, 1], [1, 2], 4) == 7
    # assert solution([3, 4], [1, 2], [2, 1], 7) == 10
    # assert solution([4, 4], [2, 2], [3, 1], 6) == 7
    # assert solution([300, 275], [150, 150], [180, 100], 500) == 9
    # assert solution([3, 4], [1, 1], [2, 2], 500) == 54243
    
if __name__ == "__main__":
    main()