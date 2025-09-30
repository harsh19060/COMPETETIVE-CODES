public class m_SearchIn2dMat {

    public boolean searchMatrix(int[][] matrix, int target) {
        boolean out = false;
        int n = matrix.length;
        int m = matrix[0].length;
        int row = 0;
        int col = m - 1;

        while (row < n && col >= 0) {
            if (matrix[row][col] == target) {
                out = true;
                return out;
            } else if (target < matrix[row][col]) {
                // move left
                col--;
            } else {
                // move down;
                row++;
            }
        }

        return out;
    }
}