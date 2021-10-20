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

int findEarliestAncestor(vector<pair<int, int>> parentChildPairs, int a, int b){
  
  unordered_map<int, vector<int>> m; //k is child, v is parent
  unordered_map<int, int> parent;  //record all parents/distance from particular node 
  
    for(int i=0;i<parentChildPairs.size();i++){
    int child = parentChildPairs[i].second;
    int parent = parentChildPairs[i].first;
    m[child].push_back(parent);
  }
  
  queue<int> q; //1 parent, 2 is distance
  
  int step = 1;
  for(auto j: m[a]){
    q.push(j);
    parent[j] = step;
  }
  
    //BFS all node a's ancestor
  while(!q.empty()){
    int cur = q.front();
    q.pop();
    step++;
    for(auto j: m[cur]){
      q.push(j);
      parent[j] = step;
    }
    
  }
  //BFS all node b's 
  int earilest = 0;
  int ret = -1;
  
  for(auto j: m[b]){
      if(parent.find(j)!=parent.end()){
        if(parent[j]>earilest){
          earilest = parent[j];
          ret = j;
        }
      }
      
      q.push(j);

  }
  
  
  while(!q.empty()){
    int cur = q.front();
    q.pop();
    for(auto j: m[cur]){
      q.push(j);
      if(parent.find(j)!=parent.end()){
        if(parent[j]>earilest){
          earilest = parent[j];
          ret = j;
        }
      }
    }
    
  }
  
  return ret;
  
}


