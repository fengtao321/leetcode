class Solution(object):
    def areSentencesSimilar(self, sentence1, sentence2):
        """
        :type sentence1: str
        :type sentence2: str
        :rtype: bool
        """
        word_list1 = sentence1.split(" ")
        word_list2 = sentence2.split(" ")
        small_list = None;
        large_list = None;
        if (len(word_list1) >= len(word_list2)):
            small_list = word_list2
            large_list = word_list1
        else:
            small_list = word_list1
            large_list = word_list2
        
        small_size = len(small_list)
        large_size = len(large_list)
        count = 0
        for i in range(0, small_size):
            if (small_list[i] == large_list[i]):
                count = count + 1
            else:
                break
        for i in range(1, small_size + 1):
            if (small_list[small_size - i] == large_list[large_size - i]):
                count = count + 1
            else:
                break
        if (count >= small_size):
            return True
        