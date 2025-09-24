
import java.util.Scanner;

public class unreachable {
    public static void main(String[] args) throws java.lang.Exception {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            boolean allOne = true;
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
                if (arr[i] == 2)
                    allOne = false;
            }

            if (allOne) {
                System.out.println("no");
                continue;
            }

            boolean odd2 = true;
            boolean even2 = true;

            for (int i = 0; i < n; i += 2) {
                if (arr[i] == 1) {
                    odd2 = false;
                    break;
                }
            }

            for (int i = 1; i < n; i += 2) {
                if (arr[i] == 1) {
                    even2 = false;
                    break;
                }
            }

            if (odd2 || even2) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }
        sc.close();
    }
}
