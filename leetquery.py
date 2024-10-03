from gql import gql, Client
from gql.transport.requests import RequestsHTTPTransport
import json

GQL_URL = "https://leetcode.com/graphql/"
GQL_QUERY = """
query problemsetQuestionList($categorySlug: String, $limit: Int, $skip: Int, $filters: QuestionListFilterInput) {
  problemsetQuestionList: questionList(
    categorySlug: $categorySlug
    limit: $limit
    skip: $skip
    filters: $filters
  ) {
    total: totalNum
    questions: data {
      acRate
      difficulty
      frontendQuestionId: questionFrontendId
      title
      titleSlug
      topicTags {
        name
      }
    }
  }
}
"""
GQL_VARS = {
  "categorySlug": "all-code-essentials",
  "skip": 0,
  "limit": 50,
  "filters": {
    "searchKeywords": "20"
  }
}

class LeetCodeQueryHandler:
    def __init__(self, url = GQL_URL, queryStr = GQL_QUERY, vars = GQL_VARS):
        self.url = url
        self.queryStr = queryStr
        self.vars = vars
        self.transport = None
        self.client = None
        self.query = None
    
    # Start/Reset the client 
    def resetClient(self, verify = True, useSchema = False):
        # Select your transport with a defined url endpoint
        self.transport = RequestsHTTPTransport(url=self.url, verify=verify)
        # Create a GraphQL client using the defined transport
        self.client = Client(transport=self.transport, fetch_schema_from_transport=useSchema)
        # Provide a GraphQL query
        self.query = gql(self.queryStr)
    
    # Set/Update the variables
    def setVars(self, vars = None):
        if (vars != None):
            self.vars = vars
        return self.vars
    
    # Update the filter keyword
    def setKeyword(self, key):
        self.vars["filters"]["searchKeywords"] = key

    
    # Do the query, and return the result
    def doQuery(self):
        result = self.client.execute(self.query, variable_values=self.vars)
        questions = result["problemsetQuestionList"]["questions"]
        # difficulty = None
        # title = None
        # titleSlug = None
        # topicTags = None
        for q in questions:
            if (int(q["frontendQuestionId"]) == int(self.vars["filters"]["searchKeywords"])):
                # difficulty = q["difficulty"]
                # title = q["title"]
                # titleSlug = q["titleSlug"]
                # topicTags = q["topicTags"]
                return q
        # print(difficulty, title, titleSlug, topicTags)
                
        return None
    


# Select your transport with a defined url endpoint
# transport = RequestsHTTPTransport(url=GQL_URL, verify=True)

# Create a GraphQL client using the defined transport
# client = Client(transport=transport, fetch_schema_from_transport=False)

# Provide a GraphQL query
# query = gql(GQL_QUERY)

if __name__ == "__main__":
    QHandler = LeetCodeQueryHandler()
    QHandler.resetClient()
    QHandler.setKeyword(60)
    result = QHandler.doQuery()
    json_file_handle = open("query_json.json", "w")
    json_obj = json.dumps(result)
    json_file_handle.write(json_obj)
    json_file_handle.close()

