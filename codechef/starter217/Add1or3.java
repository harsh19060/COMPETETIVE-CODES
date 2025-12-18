import java.lang.*;
import java.util.*;

class Add1or3
{
    public static void main(String[] args) throws java.lang.Exception
    {

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t--> 0) {
           int n = sc.nextInt();
           int m = sc.nextInt();
           if(m<n) System.out.println("no");
           else if((m-n)%2 != 0 ) System.out.println("no");
           else if((m-n)/2 > n ) System.out.println("no");
    
           else System.out.println("yes");
           

        }
    }
}