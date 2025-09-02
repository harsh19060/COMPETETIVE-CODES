//diff 1386
import java.util.HashMap;
import java.util.Scanner;

public class chefAndEquity {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();
        while (test-- > 0) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            HashMap<Integer, Integer> freq = new HashMap<>();
            for (int coins : arr) {
                freq.put(coins, freq.getOrDefault(coins, 0) + 1);
            }
            int maxFreq = 0;
            for (int value : freq.values()) {
                if (value > maxFreq) {
                    maxFreq = value;
                }
            }
            System.out.println(n-maxFreq);

        }
    }

}