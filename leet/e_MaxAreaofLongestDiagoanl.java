
// 3000. Maximum Area of Longest Diagonal Rectangle

class e_MaxAreaofLongestDiagoanl {
    public int areaOfMaxDiagonal(int[][] dimensions) {

        int diagonal = Integer.MIN_VALUE;
        int temp;
        int temp2;
        int area = Integer.MIN_VALUE;
        int op;

        for (int i = 0; i < dimensions.length; i++) {
            int length = dimensions[i][0];
            int width = dimensions[i][1];
            temp = (length * length + width * width);
            if (temp >= diagonal) {
                temp2 = length * width;
                if (temp == diagonal) {
                    op = (area < temp2) ? temp2 : area;
                    area = op;
                } else {
                    area = temp2;
                }
                diagonal = temp;
            }

        }
        return area;
    }
}
