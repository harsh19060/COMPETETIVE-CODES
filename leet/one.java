import java.util.Scanner;

public class one {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = sc.nextLine();
        if ((input.length() == 3) && (input.contains("c")) && input.contains("a") && input.contains("t")) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
        sc.close();

    }
}
