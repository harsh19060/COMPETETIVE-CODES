import java.util.Scanner;

public class trupletMAximization {
    public static void main(String[] args) throws java.lang.Exception {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int sum = 0;
            int x = sc.nextInt();
            int y = sc.nextInt();
            int o = (x + y) / 3;
            int out = o + Math.min(o, y);
            System.out.println(out);

        }

    }
}
