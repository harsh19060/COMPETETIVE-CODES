
public class maxsubarraysumbrutforce {

    public static int Subsum(int[] arry) {
        int maxsum = Integer.MIN_VALUE;
        int currSum = 0;

        for (int i = 0; i < arry.length; i++) {

            for (int j = i; j < arry.length; j++) {
                currSum = 0;
                for (int k = i; k <= j; k++) {
                    currSum += arry[k];
                }
                if (currSum > maxsum) {
                    maxsum = currSum;
                }
            }

        }
        return maxsum;
    }

    public static void main(String[] args) {
        int[] num = { 1, 2, 4, 2, 1, -3 };
        System.out.println(Subsum(num));
    }

}
