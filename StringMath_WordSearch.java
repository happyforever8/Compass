(1) String Match

    public boolean StringMatch(String str, String target){
          if (str.length() < target){
             return false;
          }
          Map<Character, Integer> map = new HashMap<>();
          
          for (char ch : str.toCharArray()){
             map.put(ch, map.getOrDefault(ch, 0) + 1);
          }
          
          for (char ch : target.toCharArray()){
              
              if (!map.containsKey(ch)){
                 return false;
              }
              map.put(ch, map.get(ch) - 1);
              if (map.get(ch) < 0){
                return false;
              }
          }
          return true;
    }



(2) Word Search

class Solution {
//     使用深度优先搜索（DFS）和回溯的思想实现。
//     关于判断元素是否使用过，我用了一个二维数组 mark 对使用过的元素做标记。
    
    
    // 在每次调用函数 check 时，除了第一次可以进入 4个分支以外，
    // 其余时间我们最多会进入 3 个分支（因为每个位置只能使用一次，所以走过来的分支没法走回去）。
    // 由于单词长为 L

    
    // time is O(mn * 3^L) l is the length of the word
    // space is O(MN) for visited arr
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        
        boolean[][] visited = new boolean[m][n];
        
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (board[i][j] == word.charAt(0)){
                    if (helper(board, visited, i, j, 0, word)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean helper(char[][] board, boolean[][] visited, int i, int j, int index, String word){
        if (index == word.length()){
            return true;
        }
        if (!inArea(board, i, j) || visited[i][j] || word.charAt(index) != board[i][j]){
            return false;
        }
        visited[i][j] = true;
        
        if (helper(board, visited, i + 1, j, index + 1, word)||
           helper(board, visited, i - 1, j, index + 1, word)||
           helper(board, visited, i, j + 1, index + 1, word)||
           helper(board, visited, i, j - 1, index + 1, word)){
            return true;
        }
        visited[i][j] = false;
        
        return false;
    }
    
    public boolean inArea(char[][] board, int i, int j){
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length){
            return false;
        }
        return true;
    }
}
