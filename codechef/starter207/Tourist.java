import java.util.*;
import java.lang.*;
import java.io.*;

class Tourist
{
	public static void main (String[] args) throws java.lang.Exception
	{
	    Scanner sc = new Scanner (System.in);
	    int t = sc.nextInt();
	    while(t-->0){
	        int c = sc.nextInt();
	       int A=sc.nextInt();
	       int B = sc.nextInt();
	       int min = Integer.MAX_VALUE;
	        for(int i = 0 ; i  < c ; i++ ){
	            int a = sc.nextInt();
	            int b = sc.nextInt();
	            min = Math.min(min,(Math.abs(A-a)+Math.abs(B-b)));
	        }
	        System.out.println(min);
	        
	    }

	}
}
