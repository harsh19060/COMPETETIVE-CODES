
import java.util.*;

class brickcompartision
{
	public static void main (String[] args) throws java.lang.Exception
	{
	    int T ;
	    Scanner sc = new Scanner (System.in);
	    T = sc.nextInt();
	    while(T-->0){
	        int n;
	        n = sc.nextInt();
	        int []arr=new int[n];
	        for (int i = 0; i< n ; i++ ){
	            arr[i]= sc.nextInt();
	        } 
	        
	        int mm =arr[0];
	        int index = 0;
	        for (int i=0;i<n ;i++ )
	        {
	           if (mm<arr[i]){
	               mm=arr[i];
	               index = i;
	           } 
	        }
	        System.out.println(index+1);
	    }

	}
}
