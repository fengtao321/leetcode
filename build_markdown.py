import os
import sys
import json
import copy

DEFAULT_PROBLEM_NAME = "Question"
LINK_PREFIX = "https://leetcode.com/problems/"
LINK_SUFFIX = "/description"
DEFAULT_LINK = "https://leetcode.com/problemset/"
LANGUAGE_PATH_KEYNAME = "Path"
LANGUAGE_SUFFIX_KEYNAME = "Suffix"
LANGUAGE_PATHS = {
    "Java": {
        LANGUAGE_PATH_KEYNAME: "./java",
        LANGUAGE_SUFFIX_KEYNAME: ".java"
    },
    "Python": {
        LANGUAGE_PATH_KEYNAME: "./python",
        LANGUAGE_SUFFIX_KEYNAME: ".py"
    },
    "C++": {
        LANGUAGE_PATH_KEYNAME: "./c++",
        LANGUAGE_SUFFIX_KEYNAME: ".cpp"
    }
}
LANGUAGE_LIST = ["Java", "Python", "C++"]

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
                       level = DEFAULT_EMPTY_STRING, tags = DEFAULT_EMPTY_STRING, solutions = {}, date = ""):
        self.number = number
        self.name = name
        self.link = link
        self.level = level
        self.tags = tags
        self.solutions = copy.deepcopy(solutions)
        self.date = date

    # Parse the filename with suffix
    def parse_file(self, filename, prefix, suffix):
        local_name = filename[:-len(suffix)] # Remove the language suffix
        strings = local_name.split(FILE_JOINSYM)
        number = strings[0]
        problem_link = LINK_PREFIX + local_name[len(number)+1:] + LINK_SUFFIX
        problem_name = ""
        for i in range(1, len(strings)):
            problem_name = problem_name + first_capital(strings[i]) + " "

        if(len(problem_name) > 0):  # Remove last space
            problem_name = problem_name[:-1]
            self.name = problem_name
        else:
            problem_link = DEFAULT_LINK
            problem_name = "Default"
        
        if(len(number) > 0):
            self.number = number
        if(len(problem_link) > 0):
            self.link = problem_link

        return number, problem_link, problem_name
    
    def set_solution(self, language, file_path):
        self.solutions[language] = file_path

    # Build the markdown line and the json object
    def build_output(self):
        markdown_lists = ["", self.number, 
                          "[{}]({})".format(self.name, self.link), 
                          self.level]
        if (self.tags != DEFAULT_EMPTY_STRING):
            markdown_lists.append(DEFAULT_BREAKLINE.join(self.tags))
        else:
            markdown_lists.append(DEFAULT_EMPTY_STRING)

        alice_lists = []
        zihao_file  = ""
        for language in LANGUAGE_LIST:
            if(language in self.solutions):
                solution_str = "[{}]({}/{})".format(
                    language, # Language name 
                    LANGUAGE_PATHS[language][LANGUAGE_PATH_KEYNAME], # Language prefix
                    self.solutions[language])
                if (language == "Java" or language == "Python"):
                    alice_lists.append(solution_str)
                else:
                    zihao_file = solution_str
        markdown_lists.append(DEFAULT_BREAKLINE.join(alice_lists))
        markdown_lists.append(zihao_file)
        markdown_lists.append(self.date)

        markdown_str = MARKDOWN_JOINSYM.join(markdown_lists) + "|\n"
        json_obj = {
            "No": self.number,
            "Name": self.name,
            "Link": self.link,
            "Level": self.level,
            "Tags": self.tags,
            "Solutions": self.solutions,
            "Date": self.date
        }

        return markdown_str, json_obj

pr_lib = Problem(-1)

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
        for language in LANGUAGE_PATHS:
            files = os.listdir(LANGUAGE_PATHS[language][LANGUAGE_PATH_KEYNAME])
            suffix = LANGUAGE_PATHS[language][LANGUAGE_SUFFIX_KEYNAME]
            for file in files:
                if(file.endswith(suffix)):
                    number, problem_link, problem_name = pr_lib.parse_file(file, None, suffix)
                    if(number.isdigit()):
                        if(int(number) not in self.problem_dict):
                            problem = Problem(number, problem_name, problem_link)
                            self.problem_dict[int(number)] = problem
                        else:
                            problem = self.problem_dict[int(number)]
                        
                        problem.set_solution(language, file)
                        #print("/".join([language, file, number, problem_link, problem_name]))

        return 0
    
    def write_output(self):
        # TODO: Call build for each problem, and write to the files

        json_raw = {}
        markdown_file_handle = open(self.markdown_filepath, "w")
        header_str = "|#No|Problem|Level|Tags|Tao|Hao|Date|\n|:-:|:-----:|:---:|:--:|:-:|:-:|:--:|\n"
        markdown_file_handle.write(header_str)
        ll = list(self.problem_dict.keys())
        ll.sort()
        for number in ll:
            problem = self.problem_dict[number]
            md_line, json_raw[number] = problem.build_output()
            markdown_file_handle.write(md_line)
            # print(number)
        json_file_handle = open(self.json_filepath, "w")
        json_obj = json.dumps(json_raw)
        json_file_handle.write(json_obj)
        json_file_handle.close()
        markdown_file_handle.close()
        return 0
    
    def shutdown(self):
        # TODO: Terminate the object
        return 0

if __name__ == "__main__":
    pr_set = ProblemSet(json_filepath = "here.json", markdown_filepath = "here.md")
    pr_set.read_files()
    pr_set.write_output()

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