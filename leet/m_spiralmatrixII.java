public class m_spiralmatrixII {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int startrow = 0, startcol = 0, endrow = n - 1;
        int endcol = n - 1;
        int temp = 1;
        while (startrow <= endrow && startcol <= endcol) {
            // top
            for (int i = startcol; i <= endcol; i++) {
                matrix[startrow][i] = temp++;
            }
            // right
            for (int i = startrow + 1; i <= endrow; i++) {
                matrix[i][endcol] = temp++;
            }
            // dowm
            for (int i = endcol - 1; i >= startcol; i--) {
                matrix[endrow][i] = temp++;
            }
            // left
            for (int i = endrow - 1; i > startrow; i--) {
                matrix[i][startcol] = temp++;
            }
            startrow++;
            endrow--;
            startcol++;
            endcol--;

        }
        return matrix;
    }
}
