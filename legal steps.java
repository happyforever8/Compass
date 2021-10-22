
import java.util.*;
public class HelloWorld{

     
     public static void main(String []args){
        System.out.println("Hello World");
        int[][] matrix = {{1,0,0,0,0},
                          {0,1,0,0,0},
                          {0,0,1,0,0},
                          {0,0,0,1,0},
                          {0,0,0,0,1}
                          };
                          
         List<int[]> list = nextSteps(matrix, 2, 3);
         
         for (int[] arr : list){
             System.out.println(arr[0] + ".."+arr[1]);
         }
         
         System.out.println(canReachToAllZero(matrix, 2, 3));
        
     }
     
     public static List<int[]> nextSteps(int[][] matrix, int a, int b){
         int[] dirX = {0, 0 , 1, -1};
         int[] dirY = {1, -1, 0, 0};
         List<int[]> result = new ArrayList<>();
         
         
         for (int i = 0; i < 4; i++){
             int nx = a + dirX[i];
             int ny = b + dirY[i];
             
             if (isInArea(matrix, nx, ny) && matrix[nx][ny] == 0){
                 result.add(new int[]{nx, ny});
             }
         }
         return result;
     }
     
     public static boolean isInArea(int[][] matrix, int nx, int ny){
         if (nx >= 0 && nx < matrix.length && ny >= 0 && ny < matrix[0].length){
             return true;
         }
         return false;
     }
     
     
     
     
     public static boolean canReachToAllZero(int[][] matrix, int a, int b){
         int m = matrix.length;
         int n = matrix[0].length;
         
         boolean[][] visited = new boolean[m][n];
         
         helper(matrix, visited, a, b);
         
         for (int i = 0; i < m; i++){
             for (int j = 0; j < n; j++){
                 if (!visited[i][j] && matrix[i][j] == 0){
                     return false;
                 }
             }
         }
         return true;
     }
     
     public static void helper(int[][] matrix,  boolean[][] visited,int a, int b){
         if (!isInArea(matrix, a, b) || matrix[a][b] == 1 ||visited[a][b]){
             return;
         }
         visited[a][b] = true;
         helper(matrix, visited, a + 1, b);
         helper(matrix, visited, a - 1, b);
         helper(matrix, visited, a, b + 1);
         helper(matrix, visited, a, b - 1);
     }
}




function findAllTreasures(board, start, end) {
  if (!board) {
    return [];
  }
  let numTreasures = 0;
  for (let i = 0; i < board.length; i++) {
    for (let j = 0; j < board[0].length; j++) {
      if (board[i][j] === 1) {
        numTreasures++;
      }
    }
  }
  const paths = [];
  const dfs = (x, y, path, remainTreasure) => {
    if (
      x < 0 ||
      x >= board.length ||
      y < 0 ||
      y >= board[0].length ||
      board[x][y] === -1 ||
      board[x][y] === 2
    ) {
      return;
    }
    path.push([x, y]);
    const temp = board[x][y];
    if (temp === 1) {
      remainTreasure--;
    }
    if (x === end[0] && y === end[1] && remainTreasure === 0) {
      paths.push([...path]);
      path.pop();
      board[x][y] = temp;
      return;
    }
    board[x][y] = 2;
    dfs(x + 1, y, path, remainTreasure);
    dfs(x - 1, y, path, remainTreasure);
    dfs(x, y + 1, path, remainTreasure);
    dfs(x, y - 1, path, remainTreasure);
    board[x][y] = temp;
    path.pop();
  };
  dfs(start[0], start[1], [], numTreasures);
  if (paths.length === 0) {
    return [];
  }
  let minPaths = paths[0].length;
  for (let i = 0; i < paths.length; i++) {
    minPaths = Math.min(minPaths, paths[i].length);
  }
  return paths.filter((path) => path.length === minPaths);
}




static List<List<int[]>> result = new ArrayList<>();
     public static void findPaths(int[][] mat,   List<int[]> path, int i, int j)
    {
        // base case
        if (mat == null || mat.length == 0) {
            return;
        }
 
       
        int M = mat.length;
        int N = mat[0].length;
 
        // if the last cell is reached, print the route
        if (i == M - 1 && j == N - 1)
        {
            path.add(new int[]{i, j});
            
            result.add(new ArrayList<>(path));
            path.remove(path.size() - 1);
            return;
        }
 
        // include the current cell in the path\
        path.add(new int[]{i, j});
 
        // move right
        if ((i >= 0 && i < M && j + 1 >= 0 && j + 1 < N) && mat[i][j] == 0) {
            findPaths(mat, path, i, j + 1);
        }
 
        // move down
        if ((i + 1 >= 0 && i + 1 < M && j >= 0 && j < N ) && mat[i][j] == 0) {
            findPaths(mat, path, i + 1, j);
        }
 
        // backtrack: remove the current cell from the path
         path.remove(path.size() - 1);
    }
 
    public static void main(String[] args)
    {
        int[][] mat =
        {
            { 0, 1, 0 },
            { 0, 0, 0 },
            { 0, 0, 0 }
        };
 
        //StringBuilder sb = new StringBuilder();
        List<int[]> path = new ArrayList<>();
        // start from `(0, 0)` cell
        int x = 0, y = 0;
 
        findPaths(mat, path, x, y);
        System.out.println(result.size());
        
        for (List<int[]> list : result){
            for (int[] step : list){
                System.out.println(step[0] + "," + step[1]);
            }
            System.out.println("============");
        }
    }
}

