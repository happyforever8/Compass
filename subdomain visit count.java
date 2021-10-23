A website domain "discuss.leetcode.com" consists of various subdomains. At the top level, we have "com", 
at the next level, we have "leetcode.com" and at the lowest level, "discuss.leetcode.com". When we visit a domain like "discuss.leetcode.com", we will also visit the parent domains "leetcode.com" and "com" implicitly.

A count-paired domain is a domain that has one of the two formats "rep d1.d2.d3" or "rep d1.d2" 
  where rep is the number of visits to the domain and d1.d2.d3 is the domain itself.

For example, "9001 discuss.leetcode.com" is a count-paired domain that indicates that
discuss.leetcode.com was visited 9001 times.
Given an array of count-paired domains cpdomains, return an array of the count-paired
domains of each subdomain in the input. You may return the answer in any order.

 

Example 1:

Input: cpdomains = ["9001 discuss.leetcode.com"]
Output: ["9001 leetcode.com","9001 discuss.leetcode.com","9001 com"]
Explanation: We only have one website domain: "discuss.leetcode.com".
As discussed above, the subdomain "leetcode.com" and "com" will also be visited. So they will all be visited 9001 times.
  
      public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();
        
        for (String str : cpdomains){
            int index = str.indexOf(' ');
            int count = Integer.parseInt(str.substring(0 , index));
            
            String domains = str.substring(index + 1);
            
            for (int i = 0; i < domains.length(); i++){
                if (domains.charAt(i) == '.'){
                    String sub = domains.substring(i + 1);
                    map.put(sub, map.getOrDefault(sub, 0) + count);
                }
            }
            map.put(domains, map.getOrDefault(domains, 0) + count);
        }
        List<String> res = new ArrayList();
        for (String d : map.keySet()) res.add(map.get(d) + " " + d);
        return res;

  
  [
  ["3234.html", "xys.html", "7hsaa.html"], // user1
  ["3234.html", "sdhsfjdsh.html", "xys.html", "7hsaa.html"] // user2
  
  
  ["xys.html", "7hsaa.html"]
]

 package fb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LCS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		String[] text1 = {"3234.html", "xys.html", "7hsaa.html"}; // user1
		String[] text2 = {"3234.html", "sdhsfjdsh.html", "xys.html", "7hsaa.html"}; // user2
		
		
		longestCommonSubsequence(text1, text2);
	}
	
    public static int longestCommonSubsequence(String[] text1, String[] text2) {
        int[][] dp = new int[text1.length + 1][text2.length + 1];
        
        int len = -1;
        List<String> list = new ArrayList<>();
        
        for (int i = 1; i <= text1.length; i++){
            for (int j = 1; j <= text2.length; j++){
            	 if (text1[i - 1].endsWith(text2[j - 1])){
            		 
            		dp[i][j] = dp[i - 1][j - 1] + 1;
            		
            		if (dp[i][j] > len){
            			len = dp[i][j];
            			String[] res = Arrays.copyOfRange(text1, i - len, i);
     
            	        list = Arrays.asList(res);	
            	        
            		}
            	} 
            }
       
        }
        
        for (String str : list){
        	System.out.println(str);
        }
        return dp[text1.length][text2.length];
    }

}
