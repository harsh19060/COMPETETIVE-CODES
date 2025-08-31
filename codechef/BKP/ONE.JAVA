import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < t; i++) {

            int date = sc.nextInt();
            String month = sc.next();
            int year = sc.nextInt();
            
            if((((date =>27 && date <= 31)&& month == "AUG")||((date >= 1 && date <= 6)&& month == "SEP")) && (year == 2025))
          {
            System.out.println("yes");
          }else {
             System.out.println("no");
          }
           
        }

        sc.close();
    }
}
