input[0] 是input[1] 的parent，⽐如
[[1,4], [1,5],
[2,5], [3,6], [6,7]]
1  2    3
/   \  /  \
4   5     6
             \
              7
第⼀问是只有0个parent和只有1个parent的节点 return all resultsList 0 & 1
第⼆问是 两个指定的点有没有公共祖先 (Time: O(N) , Space: O(N) ) return boolean
重点： method(6,3) return false 3没有祖先 不算公共祖先
在这被坑了 写完run测试才发现
第三问是 就⼀个点的最远祖先

(1) 0 or 1 node
    public List<Integer> findNode(List<int[]> relationships){
        Map<Integer, Integer> degree = new HashMap();
        for(int i = 0; i < relationships.size(); i++){
            int[] curr = relationships.get(i);
            degree.put(curr[1], degree.getOrDefault(curr[1], 0) + 1);
            degree.putIfAbsent(curr[0], 0);
        }
        List<Integer> result = new ArrayList();
        for(Map.Entry<Integer, Integer> entry : degree.entrySet()){
            int val = entry.getValue();
            if(val == 0 || val == 1){
                result.add(entry.getKey());
            }
        }
        return result;
    }
    

   **************如果input是
    List<List<Integer>> findNodesWithZeroAndOneParents(int[][] parentChildPairs)
       
       
      public List<List<Integer>>  findNode(List<int[]> relationships){
        Map<Integer, Integer> degree = new HashMap();
        for(int i = 0; i < relationships.size(); i++){
            int[] curr = relationships.get(i);
            degree.put(curr[1], degree.getOrDefault(curr[1], 0) + 1);
            degree.putIfAbsent(curr[0], 0);
        }
       List<List<Integer>>  result = new ArrayList<>();
        List<Integer> result0 = new ArrayList();
       List<Integer> result1 = new ArrayList();
        for(Map.Entry<Integer, Integer> entry : degree.entrySet()){
            int val = entry.getValue();
            if(val == 0 ){
                result0.add(entry.getKey());
            }
            if (val == 0 ){
                result1.add(entry.getKey());
            }
        }
          
        result.add(result0);
        result.add(result1);
        return result;
    }
       
  (2)两个指定的点有没有公共祖先 (Time: O(N) , Space: O(N) ) return boolean
      public static boolean commonAncestor2(int[][] pairs, int node1, int node2){
          Set<Integer> p1 = new HashSet<>(), p2 = new HashSet<>();
          help_commonAncestor2(p1, node1, pairs);
          help_commonAncestor2(p2, node2, pairs);
          for(int parent : p1){
              if(p2.contains(parent)) return true;
          }
          return false;
      }
      public static void help_commonAncestor2(Set<Integer> parents, int node, int[][] pairs){
          for(int[] pair : pairs){
              if(pair[1] == node){
                  parents.add(pair[0]);
                  help_commonAncestor2(parents, pair[0], pairs);
              }
          }
      }
      
(3)第三问是 就⼀个点的最远祖先


      public static boolean findEarliestAncestor(int[][] edges, int a){
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int[] edge:edges){
            int parent = edge[0];
            int child = edge[1];
            map.putIfAbsent(child, new ArrayList<>());
            map.get(child).add(parent);
        }
        dfs(map, a, new int[]{2}, 0);
        
        return res[1];
        }
   // 第三问：一个点的最远祖先，感觉就是用DFS做
    // int[] res来作为helper int[]， res[0] = 当前level, res[1]就是当前level的ancestor。用来打擂台
    public static void dfs(Map<Integer, List<Integer>> map, int a, int[] res, int level){
        if(level > res[0]){
            // find a higher ancestor
            // update highest level and hightes ancestor
            res[0] = level;
            res[1] = a;
        }
        List<Integer> parents = map.get(a);
        if(parents != null && parents.size()!=0){
            for(int parent:parents){
                dfs(map, parent, res, level+1);
            }
        }
    }
