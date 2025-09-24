import java.util.Scanner;

public class mirrorjump {
    public static void main(String[] args) throws java.lang.Exception {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int x = sc.nextInt();
            int out = Math.min(n - x, x);
            System.out.println(out);
        }
    }
}
