from collections import deque
class Solution:
    def maxWidthRamp(self, nums) -> int:
        # Stack queues for potential choices
        # for all greedy choices (i,j), i is in left while j in right
        left_stack = deque()
        right_stack = deque()
        left_index_stack = deque()
        right_index_stack = deque()
        # DEBUG
        def DEBUG():
            print("Left Stack: " + str(left_stack))
            print("Left Index Stack: " + str(left_index_stack))
            print("Right Stack: " + str(right_stack))
            print("Right Index Stack: " + str(right_index_stack))
        # func to add a potential choice into the stack
        def append_left(value, index):
            left_stack.append(value)
            left_index_stack.append(index)
        def append_right(value, index):
            right_stack.append(value)
            right_index_stack.append(index)
        # return 0 if only one element
        size = len(nums)
        if (size == 0):
            return 0
        # Initializations
        last = nums[0]
        append_left(last, 0)
        index = 1
        # func to find next peak
        def find_peak(start):
            temp_last = nums[start]
            for i in range(start + 1, size):
                if (nums[i] < temp_last):
                    return i - 1
                temp_last = nums[i]
            # Not found, which means we have reached the end. Return the last index
            return size - 1
                # func to find next peak
        def find_valley(start):
            temp_last = nums[start-1]
            for i in range(start, size):
                # Valley: Return the last index
                if (nums[i] >= temp_last):
                    return i - 1
                elif (nums[i] < left_stack[-1]):
                    append_left(nums[i], i)
                else:
                    append_right(nums[i], i)
                temp_last = nums[i]
            # Not found, which means we have reached the end. Return the last index
            return size - 1
        # Implement those stacks
        while (index < size):
            if (nums[index] >= last):
                print("======Before Peak at ({}, {}) ======".format(nums[index], index))
                index = find_peak(start = index)
                # Update the right stack
                while(len(right_stack) > 0):
                    if (right_stack[-1] <= nums[index]):
                        right_stack.pop()
                        right_index_stack.pop()
                    else:
                        break
                append_right(nums[index], index)
                print("======After Peak at ({}, {}) ======".format(nums[index], index))
            else:
                print("======Before Valley at ({}, {}) ======".format(nums[index], index))
                index = find_valley(start = index)
                print("======After Valley at ({}, {}) ======".format(nums[index], index))
            DEBUG()
            last = nums[index]
            index += 1
        # Make pairs
        if (len(right_stack) == 0):
            return 0
        left_element = left_stack.pop()
        left_index = left_index_stack.pop()
        right_element = right_stack.pop()
        right_index = right_index_stack.pop()
        max_ramp = 0 if right_element < left_element else right_index - left_index
        while (True):
            # Read next value accordingly
            if (right_element >= left_element):
                if (len(left_stack) == 0):
                    break
                left_element = left_stack.pop()
                left_index = left_index_stack.pop()
            else:
                if (len(right_stack) == 0):
                    break
                right_element = right_stack.pop()
                right_index = right_index_stack.pop()
            if (right_element >= left_element and right_index - left_index > max_ramp):
                max_ramp = right_index - left_index
        return max_ramp
    

if __name__ == "__main__":
    s = Solution()
    l = [1,0]
    print("===Answer: {} =====".format(s.maxWidthRamp(l)))
        