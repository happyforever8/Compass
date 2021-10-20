
Share
Given an array of strings words and a width maxWidth, format the text such that each
line has exactly maxWidth characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as
you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. 
  If the number of spaces on a line does not divide evenly between words,
the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left-justified and no extra space is inserted between words.

Note:

A word is defined as a character sequence consisting of non-space characters only.
Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.
 

Example 1:

Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]

class Solution {
//     Note:

// (1)A word is defined as a character sequence consisting of non-space characters only.
// (2)Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
// (3)The input array words contains at least one word.
    
    public List<String> fullJustify(String[] words, int maxWidth) {
        
// 第一种是添加了当前单词后也不溢出行长度要求，
//      这时候就直接放进来；
// 第二种就是加进来当前单词后就正好是行长度，
//      这时候也可以直接放进来，不过需要再把缓冲区内容放到返回值中去；
// 第三种情况就比较复杂了，需要调整空格位置和数量。
// timeComplexity is O(N)
// space Complexity is O(N)

         List<String> list = new ArrayList<>();
        //position of first word in line
        int first = 0;
        while (first < words.length) {
            int width = words[first].length();
            //position of last word in line
            int last = first + 1;
            // the reason here is +1 is because we need a space between two words
            while (last < words.length && width + words[last].length() + 1 <= maxWidth) {
                width += words[last++].length() + 1;
            }
            
            StringBuilder sb = new StringBuilder(maxWidth);
            
            // everyt character needs a space
            int numOfSpacer = last - first - 1;
            
            //last line or one word in a line, left-justified
            if (last == words.length || numOfSpacer == 0) {
                sb.append(words[first]);
                
                for (int i = first + 1; i < last; i++) {
                    sb.append(" ").append(words[i]);
                }
                
                // this is for the last line, to add the space , 
                // for example ,"justification.  "]
                for (int i = sb.length(); i < maxWidth; i++) {
                    sb.append(" ");
                }
            } else {
                // the space ammount adding to existing space
                int spaces = (maxWidth - width) / numOfSpacer;
                //extra spaces add to left spacers
                int extra = (maxWidth - width) % numOfSpacer;
                
                for (int i = first; i < last - 1; i++) {
                    sb.append(words[i]);
                    // Extra spaces between words should be 
                    //     distributed as evenly as possible. 
                    //     If the number of spaces on a line does 
                    //     not divide evenly between words,
                    // the empty slots on the left will be assigned more spaces than the slots on the right.
                    // this is to add extra space to the left
                    for (int j = 0; j <= spaces + ((i - first) < extra ? 1 : 0); j++) {
                        sb.append(" ");
                    }
                }
                sb.append(words[last - 1]);
            }
            list.add(String.valueOf(sb));
            first = last;
        }
        return list;
    }
}
