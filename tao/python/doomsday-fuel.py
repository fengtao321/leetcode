from fractions import Fraction
import numpy as np

def solution(m):
    if len(m) < 2:
        return [1, 1]

    r_subm, q_subm = split_matrix(m)
    f_subm = calc_f(q_subm)
    fr_subm = np.dot(f_subm, r_subm)
    ans = dec_to_frac_with_lcm(fr_subm[0])
    print(ans)
    return ans

def split_matrix(m):
    absorbing = find_absorbing_states(m)
    r_subm, q_subm = [], []

    for row_i, row in enumerate(m):
        if row_i not in absorbing:
            r_temp, q_temp = [], []
            row_total = sum(row)

            for col_i, value in enumerate(row):
                if col_i in absorbing:
                    r_temp.append(value / row_total)
                else:
                    q_temp.append(value / row_total)

            r_subm.append(r_temp)
            q_subm.append(q_temp)

    return r_subm, q_subm

def find_absorbing_states(m):
    absorbing = {row_i for row_i, row in enumerate(m) if sum(row) == 0}
    return absorbing

def calc_f(q_subm):
    return np.linalg.inv(np.identity(len(q_subm)) - q_subm)

def dec_to_frac_with_lcm(l):
    ret_arr, denoms = [], []

    for num in l:
        frac = Fraction(num).limit_denominator()
        ret_arr.append(frac.numerator)
        denoms.append(frac.denominator)

    lcd = calculate_lcm(denoms)

    for num_i in range(len(ret_arr)):
        ret_arr[num_i] *= lcd // denoms[num_i]

    ret_arr.append(lcd)
    return ret_arr

def calculate_lcm(numbers):
    def gcd(a, b):
        while b:
            a, b = b, a % b
        return a

    lcm = numbers[0]
    for number in numbers[1:]:
        lcm = lcm * number // gcd(lcm, number)

    return lcm

# Case where state 0 itself is a terminal state
assert(solution(
    [
        [0],
    ]
)) == [1, 1]

assert(solution(
    [
        [0, 2, 1, 0, 0],
        [0, 0, 0, 3, 4],
        [0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0],
    ]
)) == [7, 6, 8, 21]

assert(solution(
    [
        [0, 1, 0, 0, 0, 1],
        [4, 0, 0, 3, 2, 0],
        [0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0],
    ]
)) == [0, 3, 2, 9, 14]