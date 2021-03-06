给一个N*N的矩阵，判定是否是有效的矩阵。有效矩阵的定义是每一行或者每一列的数字都必须正好是1到N的数。输出一个bool。

function isValidMatrix(matrix) {
  if (!matrix || matrix.length === 0 || matrix[0].length === 0) {
    return false;
  }
  const n = matrix.length;
  for (let i = 0; i < n; i++) {
    const rowSet = new Set();
    const colSet = new Set();
    let rowMin = Number.POSITIVE_INFINITY, rowMax = Number.NEGATIVE_INFINITY;
    let colMin = rowMin, colMax = rowMax;
    for (let j = 0; j < n; j++) {
      if (!rowSet.has(matrix[i][j])) {
        rowSet.add(matrix[i][j]);
        rowMin = Math.min(rowMin, matrix[i][j]);
        rowMax = Math.max(rowMax, matrix[i][j]);
      } else {
        return false;
      }
      if (!colSet.has(matrix[j][i])) {
        colSet.add(matrix[j][i]);
        colMin = Math.min(colMin, matrix[j][i]);
        colMax = Math.max(colMax, matrix[j][i]);
      } else {
        return false;
      }
    }
    if (rowMin !== 1 || colMin !== 1 || rowMax !== n || colMax !== n) {
      return false;
    }
  }
  return true;
}

"""
A nonogram is a logic puzzle, similar to a crossword, in which the player 
is given a blank grid and has to color it according to some instructions. 
  Specifically, each cell can be either black or white, 
which we will represent as 0 for black and 1 for white.

+------------+
| 1  1  1  1 |
| 0  1  1  1 |
| 0  1  0  0 |
| 1  1  0  1 |
| 0  0  1  1 |
+------------+

For each row and column, the instructions give the lengths of 
contiguous runs of black (0) cells. For example, the instructions for
    one row of [ 2, 1 ] indicate that there must be a run of two black cells, 
followed later by another run of one black cell, and the rest of the row filled with white cells.

These are valid solutions: [ 1, 0, 0, 1, 0 ] and [ 0, 0, 1, 1, 0 ] and also [ 0, 0, 1, 0, 1 ]
This is not valid: [ 1, 0, 1, 0, 0 ] since the runs are not in the correct order.
This is not valid: [ 1, 0, 0, 0, 1 ] since the two runs of 0s are not separated by 1s.

Your job is to write a function to validate a possible solution against a set of
instructions. Given a 2D matrix representing a player's solution; and instructions
for each row along with additional instructions for each column; 
return True or False according to whether both sets of instructions match.

Example instructions #1

matrix1 = [[1,1,1,1],
           [0,1,1,1],
           [0,1,0,0],
           [1,1,0,1],
           [0,0,1,1]]
rows1_1    =  [], [1], [1,2], [1], [2]
columns1_1 =  [2,1], [1], [2], [1]
validateNonogram(matrix1, rows1_1, columns1_1) => True

Example solution matrix:
matrix1 ->
                                   row
                +------------+     instructions
                | 1  1  1  1 | <-- []
                | 0  1  1  1 | <-- [1]
                | 0  1  0  0 | <-- [1,2]
                | 1  1  0  1 | <-- [1]
                | 0  0  1  1 | <-- [2]
                +------------+
                  ^  ^  ^  ^
                  |  |  |  |
  column       [2,1] | [2] |
  instructions      [1]   [1]


Example instructions #2

(same matrix as above)
rows1_2    =  [], [], [1], [1], [1,1]
columns1_2 =  [2], [1], [2], [1]
validateNonogram(matrix1, rows1_2, columns1_2) => False

The second and third rows and the first column do not match their respective instructions.

Example instructions #3

matrix2 = [
[ 1, 1 ],
[ 0, 0 ],
[ 0, 0 ],
[ 1, 0 ]
]
rows2_1    = [], [2], [2], [1]
columns2_1 = [1, 1], [3]
validateNonogram(matrix2, rows2_1, columns2_1) => False

The black cells in the first column are not separated by white cells.

n: number of rows in the matrix
m: number of columns in the matrix
"""

  
  
  
package fb;

import java.util.ArrayList;
import java.util.List;

public class Anagram_NonAnagram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[][] matrix = {{1,1,1,1},
		                  {0,1,1,1},
		                  {0,1,0,0},
		                  {1,1,0,1},
		                  {0,0,1,1}};
		
		int[][] rows = {{}, {1}, {1,2}, {1}, {2}};
		int[][] cols = {{2,1}, {1}, {2}, {1}};
		
		int n = matrix.length;
		int m = matrix[0].length;
		
		//System.out.println(isValidNonogram(matrix, rows, cols));
		
		System.out.println(isNonogramRowsValid(matrix, rows, n, m)  && isNonogramColsValid(matrix, cols, n, m));
		
		
		
		
		int[][] matrix2 = {
		           {1,1},
		           {0,0},
		           {0,0},
		           {1,0}};
		int[][] rows2_1 = {{},{2},{2},{1}};
		int[][] columns2_1 = {{1,1},{3}};
		
		int n1 = matrix2.length;
		int m1 = matrix2[0].length;
		System.out.println(isNonogramRowsValid(matrix2, rows2_1, n1, m1) && isNonogramColsValid(matrix2, columns2_1, n1, m1));
		
		
	}
	public static boolean isValidNonogram(int[][] matrix, int[][] rows, int[][] cols) {
		  if (matrix == null  || rows == null  || cols == null) {
		    return false;
		  }
		   int n = matrix.length;
		   int m = matrix[0].length;
		  if (n == 0 || n != rows.length || m != cols.length) {
		    return false;
		  }
		  return 
		    isNonogramRowsValid(matrix, rows, n, m) && isNonogramColsValid(matrix, cols, n, m);
		 
		}

	public static boolean isNonogramRowsValid(int[][] matrix, int[][] rows, int n,int m) {
		for (int i = 0; i < n; i++){
			List<Integer> list = new ArrayList<>();
			int count = 0;
			for (int j = 0; j < m; j++){
				if (matrix[i][j] == 0){
					count++;
				} else if (count > 0){
					list.add(count);
					count = 0;
				}
			}
			if (count > 0){
				list.add(count);
			}
			if (list.size() != rows[i].length){
				return false;
			}
			for (int k = 0; k < list.size(); k++){
				if (list.get(k) != rows[i][k]){
					return false;
				}
			}
		}
		return true;
		}

	public static boolean isNonogramColsValid(int[][]matrix, int[][]cols, int n, int m) {
		for (int i = 0; i < m; i++){
			List<Integer> list = new ArrayList<>();
			int count = 0;
			for (int j = 0; j < n; j++){
				if (matrix[j][i] == 0){
					count++;
				} else if (count > 0){
					list.add(count);
					count = 0;
				}
			}
			if (count > 0){
				list.add(count);
			}
			if (list.size() != cols[i].length){
				return false;
			}
			for (int k = 0; k < list.size(); k++){
				if (list.get(k) != cols[i][k]){
					return false;
				}
			}
		}
		return true;
		}
}
