
import java.util.Scanner;

public class cuteString {
    public static void main(String[] args) throws java.lang.Exception {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        if (s.length() == 3 && s.charAt(0) == s.charAt(2) && s.charAt(1) == 'w') {
            System.out.println("Cute");
        } else {
            System.out.println("no");
        }

    }
}
