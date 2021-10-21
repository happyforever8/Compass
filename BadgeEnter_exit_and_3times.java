package Phone;

import java.util.*;

public class BadgeAndCompany {
    /**
     Question 1:
     We are working on a security system for a badged-access room in our company's building.
     1. Given an ordered list of employees who used their badge to enter or exit the room, write a function that returns two collections:
     a. All employees who didn't use their badge while exiting the room – they recorded an enter without a matching exit.
     b. All employees who didn't use their badge while entering the room – they recorded an exit without a matching enter.
     Sample input:
     badge_records = [
     ["Martha",   "exit"],
     ["Paul",     "enter"],
     ["Martha",   "enter"],
     ["Martha",   "exit"],
     ["Jennifer", "enter"],
     ["Paul",     "enter"],
     ["Curtis",   "enter"],
     ["Paul",     "exit"],
     ["Martha",   "enter"],
     ["Martha",   "exit"],
     ["Jennifer", "exit"]
     ]
     // input: records: [[name1, exit/enter], [name2, exit/enter]]
     // Return two list, [noExit, noEnter]
     **/

    public List<List<String>> findMismatchedEntries(String[][] records) {
        // 这个进出的顺序是有用的，不能打乱。
        Map<String, Stack<String>> map = new HashMap<>(); // <name, sequence of enter/exit>
        for (String[] record : records) {
            map.putIfAbsent(record[0], new Stack<String>());
            if (record[1].equals("exit")) {
                if (!map.get(record[0]).isEmpty() && map.get(record[0]).peek().equals("enter")) {
                    map.get(record[0]).pop();
                    continue;
                }
            }
            map.get(record[0]).push(record[1]);
        }
        List<String> missEnter = new ArrayList<>();
        List<String> missExit = new ArrayList<>();
        for (String key : map.keySet()) {
            Set<String> temp = new HashSet<>(map.get(key));
            if (temp.contains("enter")) {
                missEnter.add(key);
            }
            if (temp.contains("exit")) {
                missExit.add(key);
            }
        }
        return new ArrayList<>(Arrays.asList(missEnter, missExit));
    }

}


more than 3 times within one hour

给 list of [name, time], time is string format: '1300' //下午一点
return: list of names and the times where their swipe badges within one hour. 
if there are multiple intervals that satisfy the condition, return any one of them.
name1: time1, time2, time3...
name2: time1, time2, time3, time4, time5...
example:
input: [['James', '1300'],
        ['Martha', '1600'], 
        ['Martha', '1620'],
        ['Martha', '1530']] 
output: {
'Martha': ['1600', '1620', '1530']
}


class Solution {
  public static void main(String[] args) {
    ArrayList<String> strings = new ArrayList<String>();
    strings.add("Hello, World!");
    strings.add("Welcome to CoderPad.");
    strings.add("This pad is running Java " + Runtime.version().feature());

    for (String string : strings) {
      System.out.println(string);
    }
    
    
    String[][] records = {{"Jame", "1300"}, {"Martha", "1600"}, {"Martha", "1620"}, {"Martha", "1530"}}; 
    
    
    String[][] records1 = {
{"Paul", "1355"},
{"Jennifer", "1910"},
{"John", "830"},
{"Paul", "1315"},
{"John", "1615"},
{"John", "1640"},
{"John", "835"},
{"Paul", "1405"},
{"John", "855"},
{"John", "930"},
{"John", "915"},
{"John", "730"},
{"Jennifer", "1335"},
{"Jennifer", "730"},
{"John", "1630"},
};
    frequentAccess(records1);
    
  }
  
  public static Map<String, List<String>> frequentAccess(String[][] records){
    Map<String, List<String>> result = new HashMap<>();
    
    if (records == null || records.length == 0){
      return result;
    }
    
    Map<String, List<String>> map = new HashMap<>();
    
    for (String[] record : records){
        
        map.putIfAbsent(record[0], new ArrayList<>());
        map.get(record[0]).add(record[1]);
    }
    
    for (Map.Entry<String,List<String>> entry : map.entrySet()){
      List<String> timeList = entry.getValue();
      
      Collections.sort(timeList);
      
      int start = 0;
      
      
      List<String> curr = new ArrayList<>();
      
      curr.add(timeList.get(start));
      
      for (int end = 1; end < timeList.size(); end++){
        if (timeDiff(timeList.get(start), timeList.get(end))){
          
            curr.add(timeList.get(end));
 
        } else {
           curr.clear();
           curr.add(timeList.get(end));
           start = end;
        }
      }
      
      if (curr.size() >= 3){
        result.put(entry.getKey(), curr);
        System.out.print(entry.getKey() + ":");
        
        for (String str : curr){
         System.out.print(str + " ");
        }
      }
    }
    
    return result;
  }
  
  public static boolean timeDiff(String time1, String time2){
    int t1 = Integer.parseInt(time1);
    int t2 = Integer.parseInt(time2);
    
    return Math.abs(t1 - t2) <= 100;
  }
}
