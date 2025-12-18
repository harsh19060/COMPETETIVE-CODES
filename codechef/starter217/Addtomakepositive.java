import java.lang.*;
import java.util.*;

class Addtomakepositive
{
	public static void main (String[] args) throws java.lang.Exception
	{
	    Scanner sc= new Scanner(System.in);
	    int t = sc.nextInt();
	    while(t-->0){
	    int n = sc.nextInt();
	    int sum = 0;
	    for(int i = 0 ; i< n ; i++){
	        sum+=sc.nextInt();
	    }
	    int count = 0;
	        while(sum<0){
	            count++;
	            sum +=n;
	        }
	    System.out.println(count);
	    
	}}
}
