package codechef.starter206;

import java.util.Scanner;

public class PrimeSum {
     public static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) throws java.lang.Exception
    {


        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t--> 0) {
            int n = sc.nextInt();
            int p[] = new int[n];
            for (int i = 0; i < n; i++) {
                p[i] = sc.nextInt();
            }
            int count = 0;
            boolean f =false;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    f =isPrime(p[i]+p[j]);
                    if(f) count++;
                }
            }
            System.out.println(count);
        }

}
