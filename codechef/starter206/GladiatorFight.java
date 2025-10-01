package codechef.starter206;

import java.util.Scanner;

public class GladiatorFight {
   	public static void main (String[] args) throws java.lang.Exception
	{
	    Scanner sc =new Scanner(System.in);
	    int t = sc.nextInt();
	    while(t-->0){
	        int n = sc.nextInt();
            int min = (n - 2);
            int max = (n - 1)*(n-2)/2;
            System.out.println(min + " " + max);
	        }

	    }
}
