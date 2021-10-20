Find All Anagrams in a String

Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

 

Example 1:

Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
  
  
  class Solution {
    
    // time is O(m + n)
    // space is O(n) // n is length of p
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        
        if (s.length() < p.length()){
            return result;
        }
        
        Map<Character, Integer> map = new HashMap<>();
        
        for (char ch : p.toCharArray()){
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        
        int count = map.size();
        
        int start = 0;
        int end = 0;
        
        while (end < s.length()){
            char ch = s.charAt(end);
                
            if (map.containsKey(ch)){
                map.put(ch, map.get(ch) - 1);
                if (map.get(ch) == 0){
                    count--;
                }
            }
            
            end++;
            
            while (count == 0){
                char c = s.charAt(start);
                
                if (map.containsKey(c)){
                    map.put(c, map.get(c) + 1);
                    if (map.get(c) > 0){
                        count++;
                    }
                }
                
                if (end - start == p.length()){
                    result.add(start);
                }
                start++;
                
            }
        }
        return result;
    }
}
