ode题是一题两个难度，
Lv1:
Given a MxN array, it contains only 0 or 1. 0 is space and 0 is wall.
  A snake can only move froward, return which rows/columns it can walk through.
不记得原题是什么编号了，既可以行列暴力扫描（2便），也可以统计每行/列0的个数之和（1遍扫描）

Lv2:
Now the snake is smarter, it can turn left/right when 
seeing the wall. If the snake enters at [i,j], return the nearest exist, [-1,-1] if not available.

Note it cannot leave a‍‍‍‍‍‍‍‍‌‍‌‌‍‌‌‌‌‌t position [i,j].
这个题是经典的BFS例子了。

import java.util.*;
public class HelloWorld{

     
     public static void main(String []args){
        System.out.println("Hello World");
        int[][] matrix = {{0,0,0,0,0},
                          {0,1,0,0,0},
                          {0,0,1,0,0},
                          {0,0,0,1,0},
                          {0,0,0,0,0}
                          };
                          
         List<Integer> list = helper(matrix);
         
         for (int num : list){
             System.out.println(num);
         }
         
         int[] arr = nearestExit(matrix, 2, 1);
         for (int num : arr){
             System.out.println(num);
         }
     }
     
     public static List<Integer> helper(int[][] matrix){
         int m = matrix.length;
         int n = matrix[0].length;
         List<Integer> result = new ArrayList<>();
         
         int[] row = new int[m];
         int[] col = new int[n];
         
         for (int i = 0; i < n; i++){
             for (int j = 0; j < m; j++){
                 if (matrix[i][j] == 0){
                     row[i]++;
                     col[j]++;
                     if (row[i] == n){
                         result.add(i);
                     }
                     if (col[j] == m){
                         result.add(j);
                     }
                 }
             }
         }
         return result;
        
     }

     public static int[] nearestExit(int[][] matrix, int a, int b){
         int n = matrix.length;
         int m = matrix[0].length;
        int[] dirX = {0, 1};
        int[] dirY = {1, 0};
         Queue<int[]> queue = new LinkedList<>();
        //  boolean[][] visited = new int[][];
         
         queue.offer(new int[]{a, b});
        //  visited[i][j] = true;
         
         while (!queue.isEmpty()){
             int size = queue.size();
             
             for (int i = 0; i < size; i++){
                 int[] curr = queue.poll();
                 
                 for (int j = 0; j < 2; j++){
                     int nx = curr[0] + dirX[j];
                     int ny = curr[1] + dirY[j];
                     
                     if (nx >= 0 && nx < n && ny >= 0 && ny <= m && matrix[nx][ny]  != 1){
                         if (nx == 0 || nx == n - 1 || ny == 0 || ny == m){
                             return new int[]{nx, ny};
                         }
                         queue.offer(new int[]{nx, ny});
                         //visited[i][j] = true;
                     }
                 }
             }
         }
         return new int[]{-1, -1};
     }
}

