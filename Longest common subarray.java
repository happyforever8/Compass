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
