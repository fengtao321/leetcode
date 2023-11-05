from math import sqrt, ceil, atan2

def solution(dimensions, your_position, trainer_position, distance):
    # calculate maximum repetiions of current room in mirrored room
    # cp_x = int(ceil((your_position[0] + distance) / dimensions[0]))
    # cp_y = int(ceil((your_position[1] + distance) / dimensions[1]))
    cp_x = ceil_div((your_position[0] + distance), dimensions[0])
    cp_y = ceil_div((your_position[1] + distance), dimensions[1])
    # generate all possible positions in q1
    q1_player = [your_position]
    q1_trainer = [trainer_position]
    for i in range(0, cp_x):
        for j in range(0, cp_y):
            if i == 0 and j == 0:
                continue
            else:
                temp_player = [your_position[0] + i * dimensions[0], your_position[1] + j * dimensions[1]]
                temp_trainer = [trainer_position[0] + i * dimensions[0], trainer_position[1] + j * dimensions[1]]
                if i % 2 != 0:
                    temp_player[0] = temp_player[0] - (2 * your_position[0]) + dimensions[0]
                    temp_trainer[0] = temp_trainer[0] - (2 * trainer_position[0]) + dimensions[0]
                if j % 2 != 0:
                    temp_player[1] = temp_player[1] - (2 * your_position[1]) + dimensions[1]
                    temp_trainer[1] = temp_trainer[1] - (2 * trainer_position[1]) + dimensions[1]
                q1_player.append(temp_player)
                q1_trainer.append(temp_trainer)

    # generate all possible positions in q2, q3, and q4
    q2_player = [[-x, y] for [x, y] in q1_player]
    q2_trainer = [[-x, y] for [x, y] in q1_trainer]
    q3_player = [[-x, -y] for [x, y] in q1_player]
    q3_trainer = [[-x, -y] for [x, y] in q1_trainer]
    q4_player = [[x, -y] for [x, y] in q1_player]
    q4_trainer = [[x, -y] for [x, y] in q1_trainer]

    # generate distances from original player
    player = [[x, y, dist(your_position, [x, y]), 1] for [x, y] in q1_player + q2_player + q3_player + q4_player]
    trainer = [[x, y, dist(your_position, [x, y]), 2] for [x, y] in q1_trainer + q2_trainer + q3_trainer + q4_trainer]
    corners = [[x, y, dist(your_position, [x, y]), 3] for [x, y] in [(0, 0), (dimensions[0], 0), (dimensions[0], dimensions[1]), (0, dimensions[1])]]

    # filter for distances that are too far away
    positions = filter(lambda x: x[2] <= float(distance), player + trainer + corners)
    positions = sorted(positions, key=lambda x: x[2]) # so least distance is always first


    # reduce list of lists with same angle but grab least distance
    angles = {}
    for pos in positions[1:]:
        agl = ang(your_position, [pos[0], pos[1]])
        if agl not in angles:
            angles[agl] = pos
            
    # uncomment to see the list of vectors
    # print([(pos[0] - your_position[0], pos[1] - your_position[1]) for pos in angles.values() if pos[4] == 2])

    # return number of times only trainer is hit
    return sum(1 for pos in angles.values() if pos[3] == 2)

def dist(p1, p2):
    return sqrt((p1[0] - p2[0])**2 + (p1[1] - p2[1])**2)

def ang(p1, p2):
    return atan2((p2[1] - p1[1]), (p2[0] - p1[0]))

def ceil_div(quotient, divisor):
    return (quotient + divisor - 1) // divisor

def main():
    # print(solution([3, 2], [1, 1], [2, 1], 4))
    # print(solution([2, 5], [1, 2], [1, 4], 11))
    print(solution([3, 4], [1, 1], [2, 2], 500))
    assert solution([3, 2], [1, 1], [2, 1], 4) == 7
    assert solution([2, 5], [1, 2], [1, 4], 11) == 27
    assert solution([23, 10], [6, 4], [3, 2], 23) == 8
    assert solution([1250, 1250], [1000, 1000], [500, 400], 10000) == 196
    assert solution([10, 10], [4, 4], [3, 3], 5000) == 739323
    assert solution([3, 2], [1, 1], [2, 1], 7) == 19
    assert solution([2, 3], [1, 1], [1, 2], 4) == 7
    assert solution([3, 4], [1, 2], [2, 1], 7) == 10
    assert solution([4, 4], [2, 2], [3, 1], 6) == 7
    assert solution([300, 275], [150, 150], [180, 100], 500) == 9
    assert solution([3, 4], [1, 1], [2, 2], 500) == 54243
    
if __name__ == "__main__":
    main()