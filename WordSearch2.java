Given an m x n board of characters and a list of strings words, return all words on the board.

Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are
horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
  
  
  class Solution {
    
    //O(M(4⋅3 ^ (L−1) )),where M is the number of cells in the board and L is the maximum length of words.
    //Space Complexity: )O(N), where N is the total number of letters in the dictionary.
     TrieNode root = new TrieNode();
    
    public List<String> findWords(char[][] board, String[] words) {
        Set<String> result = new HashSet<>();
        
        int m = board.length;
        int n = board[0].length;
        
        for (String str : words){
            insert(str);
        }
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                helper(board, visited, i, j, "", result);
            }
        }
        return new ArrayList<>(result);
        
    }
    
    public void helper(char[][] board, boolean[][] visited, int i, int j, String str, Set<String> result){
        if ( i >= board.length || i < 0 || j >= board[0].length || j < 0 || visited[i][j]){
            return;
        }
        str += board[i][j];
        if (!startWith(str)){
            return;
        }
        if (search(str)){
            result.add(str);
        }
        visited[i][j] = true;
        helper(board, visited, i + 1, j, str, result);
         helper(board, visited, i - 1, j, str, result);
         helper(board, visited, i, j + 1, str, result);
         helper(board, visited, i, j - 1, str, result);
        visited[i][j] = false;
    }
    
    public void insert(String str){
        TrieNode node = root;
        
        for (char ch : str.toCharArray()){
            if (node.children[ch - 'a'] == null){
                node.children[ch - 'a'] = new TrieNode();
            }
            node = node.children[ch - 'a'];
        }
        node.isWord = true;
    }
    
    public boolean startWith(String prefix){
        TrieNode node = root;
        
        for (char ch : prefix.toCharArray()){
            if (node.children[ch - 'a'] == null){
                return false;
            }
            node = node.children[ch - 'a'];
        }
        return true;
    }
    public boolean search(String str){
        TrieNode node = root;
        
        for (char ch : str.toCharArray()){
            if (node.children[ch - 'a'] == null){
                node.children[ch - 'a'] = new TrieNode();
            }
            node = node.children[ch - 'a'];
        }
        return node.isWord;
    }
    
    
}

class TrieNode{
    TrieNode[] children;
    boolean isWord;
    
    public TrieNode(){
        children = new TrieNode[26];
        isWord = false;
    }
}
