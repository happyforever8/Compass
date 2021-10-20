一道题理解非常绕，就是given Stringp[][] records,输入数据像
[["james", "enter"], ["murthy", "exit"] ...]等，
enter表示这个人用badge进入room，
exit表示这个人用badge出去room，
然后结果是返回两个collections of names，
第一个collection要求所有没有用badge exit room的人-即那些在does not have corresponding enter for current exits的人的名字，第二个相反的，
没有用badge enter room的人的列表，即那些在does not have corresponding exit for current enter的人的名字，这里lz直接上code了

public static List<List<String>> getSolution(String[][] records){

        // skip sanity check

        List<List<String>> ans = new ArrayList<>();

        Map<String, List<String>> map = new HashMap<>(); // space: O(n) where n is the number of pairs

        for(String[] record: records){ // time complexity: O(n)
            List<String> enter_exit = map.getOrDefault(record[0], new ArrayList<>());
            enter_exit.add(record[1]);
            map.put(record[0], enter_exit);
        }

        List<String> c1 = new ArrayList<>();
        List<String> c2 = new ArrayList<>();

        // core logics
        for(Map.Entry<String, List<String>> entry: map.entrySet()){ 
            // O(n) where m is the unique number of names, the n is the total of records such as enter or exits
            // space O(m+n) wehre m is unique of name,
            int index = 0;
            Stack<Integer> s = new Stack<>();
            List<String> tmp = entry.getValue();
            String name = entry.getKey();
            boolean isPrevExit = false;
            while(index < tmp.size()){
                if(tmp.get(index).equals("enter")) {
                    if(!s.isEmpty()) {
                        c1.add(name);
                     }
                    s.add(index);
                }

                else {
                    if(isPrevExit){
                      c2.add(name);
                    }
                    isPrevExit = true;
                    if(s.isEmpty()) {
                      c2.add(name);
                    } else {
                      s.pop();
                    }
                }
                index++;

            }
            if(!s.isEmpty()) c1.add(name);
        }

        Set<String> filter_c1 = new HashSet<>(c1);
        Set<String> filter_c2 = new HashSet<>(c2);
        ans.add(new ArrayList<>(filter_c‍‍‍‍‍‍‍‍1));
        ans.add(new ArrayList<>(filter_c2));

        return ans;

    }
