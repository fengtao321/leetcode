from math import sqrt
from numpy import zeros


def computeDist(p1, p2, distance):
    return distance**2 <= (p1[0] - p2[0])**2 + (p1[1] - p2[1])**2


def computeGCD(x, y):
    while(y):
        x, y = y, x % y
    return abs(x)


def get_entity_position_from_room_number(entity, room_number, dimensions):
    r_x, r_y = room_number
    e_x, e_y = entity
    dim_x, dim_y = dimensions

    res_x = dim_x*r_x + e_x if r_x % 2 == 0 else dim_x*r_x + (dim_x - e_x)
    res_y = dim_y*r_y + e_y if r_y % 2 == 0 else dim_y*r_y + (dim_y - e_y)

    return (res_x, res_y)


def solution(dimensions, your_position, trainer_position, distance):

    dim_x, dim_y = dimensions
    m_x, m_y = your_position

    num_rooms_above_x_axis = (distance + m_y )//dim_y + 2
    num_rooms_below_x_axis = (distance - m_y )//dim_y + 2
    num_rooms_left_of_y_axis = (distance - m_x)//dim_x + 2
    num_rooms_right_of_y_axis = (distance + m_x)//dim_x + 2
    # print(num_rooms_above_x_axis, num_rooms_below_x_axis, num_rooms_right_of_y_axis, num_rooms_left_of_y_axis)

    w = (num_rooms_right_of_y_axis + num_rooms_left_of_y_axis)*dim_x + 1
    h = (num_rooms_above_x_axis + num_rooms_below_x_axis)*dim_y + 1

    x_offset = num_rooms_left_of_y_axis*dim_x
    y_offset = num_rooms_below_x_axis*dim_y

    matrix = zeros(shape=(w, h))
    for i in range(-1*num_rooms_left_of_y_axis, num_rooms_right_of_y_axis):
        for j in range(-1*num_rooms_below_x_axis, num_rooms_above_x_axis):
            tv_x, tv_y = get_entity_position_from_room_number(
                trainer_position, [i, j], dimensions)
            mv_x, mv_y = get_entity_position_from_room_number(
                your_position, [i, j], dimensions)

            matrix[tv_x+x_offset][tv_y+y_offset] = 1
            matrix[mv_x+x_offset][mv_y+y_offset] = 2
    # print(matrix)
    hits = 0
    shots_taken = set()
    ans = []
    for i in range(-1*num_rooms_left_of_y_axis, num_rooms_right_of_y_axis):
        for j in range(-1*num_rooms_below_x_axis, num_rooms_above_x_axis):
            t_x, t_y = get_entity_position_from_room_number(
                trainer_position, [i, j], dimensions)
            if computeDist([t_x, t_y], your_position, distance):
                continue
            delta_y = t_y - m_y
            delta_x = t_x - m_x
            d = computeGCD(delta_y, delta_x)
            delta_y = delta_y//d
            delta_x = delta_x//d
            if (delta_y, delta_x) in shots_taken:
                continue
            shots_taken.add((delta_y, delta_x))
            ray_x, ray_y = m_x + x_offset, m_y + y_offset
            while True:
                ray_x += delta_x
                ray_y += delta_y
                entity = matrix[ray_x][ray_y]
                if entity == 1:
                    hits += 1
                    ans.append((delta_x, delta_y))
                    break
                elif entity == 2:
                    break
    # ans.sort()
    # print(ans)
    return hits


def main():
    # print(solution([3, 2], [1, 1], [2, 1], 4))
    print(solution([3, 4], [1, 1], [2, 2], 500))
    # print(solution([3, 4], [1, 1], [2, 2], 500))
    # assert solution([3, 2], [1, 1], [2, 1], 4) == 7
    # assert solution([2, 5], [1, 2], [1, 4], 11) == 27
    # assert solution([23, 10], [6, 4], [3, 2], 23) == 8
    # assert solution([1250, 1250], [1000, 1000], [500, 400], 10000) == 196
    # assert solution([10, 10], [4, 4], [3, 3], 5000) == 739323
    # assert solution([3, 2], [1, 1], [2, 1], 7) == 19
    # assert solution([2, 3], [1, 1], [1, 2], 4) == 7
    # assert solution([3, 4], [1, 2], [2, 1], 7) == 10
    # assert solution([4, 4], [2, 2], [3, 1], 6) == 7
    # assert solution([300, 275], [150, 150], [180, 100], 500) == 9
    # assert solution([3, 4], [1, 1], [2, 2], 500) == 54243
    
if __name__ == "__main__":
    main()