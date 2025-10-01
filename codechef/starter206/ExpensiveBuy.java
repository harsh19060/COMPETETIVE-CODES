package codechef.starter206;

import java.util.Arrays;
import java.util.Scanner;

public class ExpensiveBuy {
    public static void main (String[] args) throws java.lang.Exception
	{
	
	Scanner sc =new Scanner(System.in);
	int t = sc.nextInt();
	while(t-->0){
	    int size = sc.nextInt();
	    int n = sc.nextInt();
	    int arr[] =new int[size];
	    for(int i = 0 ; i< size ;i++){
	        arr[i]=sc.nextInt();
	    }
	    Arrays.sort(arr);
	    int out=0;
	    for(int i = size-1 ; i >=size- n ;i--){
	        out+= arr[i];
	    }
	    System.out.println(out);
	}

	}
}
