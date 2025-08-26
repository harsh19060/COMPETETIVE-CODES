



public class que6_two {
    public static void main(String[] args) {
        int[][] matrix = {
            {3, 8, 7},
            {5, 9, 6},
            {4, 2,10}
        };

        int n = matrix.length;        // rows
        int m = matrix[0].length;     // columns
        boolean found = false;

        for (int i = 0; i < n; i++) {
            // Step 1: Find min in row i
            int rowMin = matrix[i][0];
            int colIndex = 0;
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] < rowMin) {
                    rowMin = matrix[i][j];
                    colIndex = j;
                }
            }

            // Step 2: Check if rowMin is max in its column
            boolean isSaddle = true;
            for (int k = 0; k < n; k++) {
                if (matrix[k][colIndex] > rowMin) {
                    isSaddle = false;
                    break;
                }
            }

            if (isSaddle) {
                System.out.println("Saddle Point found: " + rowMin + " at (" + i + "," + colIndex + ")");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No Saddle Point in matrix");
        }
    }
}
