from functools import lru_cache
    
def solution(balls):    
    ans = 0
    num_R = num_G = num_B = 0
    
    for c in balls:
        match c:
            case "R": num_R+=1
            case "G": num_G+=1
            case _: num_B+=1
    
    for i in range(0, num_R):
        if balls[i] == "R": continue 
        ans+=1
    
    for i in range(num_R, num_R+num_G):
        if balls[i] == "B": ans+=1

    return ans
        
        
def main():
    print(solution("RRGGBB"), 0)
    print(solution("RGBRGB"), 2)
    
if __name__ == "__main__":
    main()