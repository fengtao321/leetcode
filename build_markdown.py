import os
import sys

DEFAULT_PROBLEM_NAME = "Question"
LINK_PREFIX = "https://leetcode.com/problems/"
LINK_SUFFIX = "/description"
DEFAULT_LINK = "https://leetcode.com/problemset/"
LANGUAGE_PATHS = {"Java": "./java", "Python": "./Python", "C++": "/C++"}
LANGUAGE_LIST = ["Java", "Python", "C++"]
CPP_SUFFIX = ".cpp"
PYTHON_SUFFIX = ".py"
JAVA_SUFFIX = ".java"

DEFAULT_SOLUTION_PATH = ""
DEFAULT_EMPTY_STRING = "-"
DEFAULT_BREAKLINE = "<br>"

MARKDOWN_JOINSYM = "|"
FILE_JOINSYM = "-"

index = []
dict = {}
folders = {"Java": "./java", "Python": "./Python"}#, "C++": "/C++"}

# detail_dict = {"Name": "Two Sum", "Link": ".../two-sum/descriptions", "AliceFile": "./Python/1-two-sum.py"}

# Make first letter to upper if applicable
def first_capital(str):
    if(len(str) > 0):
        if('a' <= str[0] and str[0] <= 'z'):
            return str[0].upper() + str[1:]
    return str

class Problem:
    def __init__(self, number, name = DEFAULT_PROBLEM_NAME, link = DEFAULT_LINK, 
                       level = DEFAULT_EMPTY_STRING, tags = DEFAULT_EMPTY_STRING, solutions = {}):
        self.number = number
        self.name = name
        self.link = link
        self.level = level
        self.tags = tags
        self.solutions = solutions

    # Parse the filename with suffix
    def parse_file(self, filename, prefix, suffix):
        local_name = filename[:-len(suffix)] # Remove the language suffix
        strings = local_name.split(FILE_JOINSYM)
        number = strings[0]
        problem_link = local_name

        problem_name = ""
        for i in range(1, len(strings)):
            problem_name = problem_name + first_capital(strings[i]) + " "

        if(len(problem_name) > 0):  # Remove last space
            problem_name = problem_name[:-1]
            self.name = problem_name
        
        if(len(number) > 0):
            self.number = number
        if(len(problem_link) > 0):
            self.link = problem_link

        return number, problem_link, problem_name
    
    def set_solution(self, language, file_path):
        self.solutions[language] = file_path

    # Build the markdown line and the json object
    def build_output(self):
        markdown_lists = [self.number, self.name, self.level]
        if (self.tags != DEFAULT_EMPTY_STRING):
            markdown_lists.append(DEFAULT_BREAKLINE.join(self.tags))
        else:
            markdown_lists.append(DEFAULT_EMPTY_STRING)

        for language in LANGUAGE_LIST:
            markdown_lists.append(self.solutions[language])

        markdown_str = MARKDOWN_JOINSYM.join(markdown_lists)
        json_obj = {
            "No": self.number,
            "Name": self.name,
            "Link": self.link,
            "Level": self.level,
            "Tags": self.tags,
            "Solutions": {
                "Java": self.solutions['Java'],
                "Python": self.solutions['Python'],
                'C++': self.solutions['C++']
            }
        }

        return markdown_str, json_obj

class ProblemSet:
    def __init__(self, json_filepath, markdown_filepath):
        self.json_filepath = json_filepath
        self.markdown_filepath = markdown_filepath
        self.problem_dict = {}
    
    def read_json(self):
        # TODO: Load the Json file into the dict object

        # TODO: Iterate the dict and create Problem object
        return 0
    
    def read_files(self):
        # TODO: Go through all files to parse. Append any if missing in the current problem
        return 0
    
    def write_output(self):
        # TODO: Call build for each problem, and write to the files
        return 0
    
    def shutdown(self):
        # TODO: Terminate the object
        return 0
    

# def read_language(language):
#     language_path = folders[language]
#     files = os.listdir(language_path)
#     for file in files:
#         #print(file)
#         detail_dict, number = regex_read(file)
#         dict[number] = detail_dict
#         index.append(number)

# def main():
#     for language in folders:
#         read_language(language)
    
#     markdown_file = open("temp_markdown.md", "w+")
#     index.sort()
#     for number in index:
#         write_tablerow(dict[number])

#     markdown_file.close()

# if __name__ == "__main__":
#     main()