import os
import sys

index = []
dict = {}
folders = {"Java": "./java", "Python": "./Python"}#, "C++": "/C++"}

# detail_dict = {"Name": "Two Sum", "Link": ".../two-sum/descriptions", "AliceFile": "./Python/1-two-sum.py"}

def read_language(language):
    language_path = folders[language]
    files = os.listdir(language_path)
    for file in files:
        #print(file)
        detail_dict, number = regex_read(file)
        dict[number] = detail_dict
        index.append(number)

def main():
    for language in folders:
        read_language(language)
    
    markdown_file = open("temp_markdown.md", "w+")
    index.sort()
    for number in index:
        write_tablerow(dict[number])

    markdown_file.close()

if __name__ == "__main__":
    main()