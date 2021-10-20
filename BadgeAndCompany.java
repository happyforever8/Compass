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
