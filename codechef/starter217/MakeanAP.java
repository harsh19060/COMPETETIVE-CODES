import java.lang.*;
import java.util.*;

class MakeanAP
{
    static int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    public static void main(String[] args) throws java.lang.Exception
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();

            int arr[] = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            if (n <= 2) {
                System.out.println(0);
                continue;
            }

            int diff[] = new int[n - 1];
            for (int i = 0; i < diff.length; i++) {
                diff[i] = arr[i + 1] - arr[i];
            }

            int D = diff[0];
            for (int i = 1; i < diff.length; i++) {
                D = gcd(D, diff[i]);
            }

            int op = 0;
            for (int i = 0; i < diff.length; i++) {
                op += (diff[i] / D) - 1;
            }

            System.out.println(op);
        }
    }
}
