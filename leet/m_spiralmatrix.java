import java.util.ArrayList;
import java.util.List;

public class m_spiralmatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> ll = new ArrayList<>();
        // n*m matrix
        int n = matrix.length;
        int m = matrix[0].length;
        int startrow = 0;
        int endrow = n - 1;
        int startcol = 0;
        int endcol = m - 1;

        while (startrow <= endrow && startcol <= endcol) { // top
            for (int i = startcol; i <= endcol; i++) {
                ll.add(matrix[startrow][i]);
            }

            // right
            for (int i = startrow + 1; i <= endrow; i++) {
                ll.add(matrix[i][endcol]);

            }
            // bottom

            for (int i = endcol - 1; i >= startcol; i--) {
                if (startrow == endrow) {
                    break;
                }
                ll.add(matrix[endrow][i]);
            }

            // left
            for (int i = endrow - 1; i >= startrow + 1; i--) {
                if (startcol == endcol) {
                    break;
                }
                ll.add(matrix[i][startcol]);

            }
            startrow++;
            startcol++;
            endcol--;
            endrow--;
        }
        return ll;
    }
}
