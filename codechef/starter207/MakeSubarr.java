import java.util.*;
import java.lang.*;
import java.io.*;

class MakeSubarr
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t-- > 0){
		    int size = sc.nextInt();
		    String s = sc.next();
		    int total = 0;
		    boolean foundFirstOne = false;
		    for(int i = 0; i < size; i++){
		        if(s.charAt(i) == '1'){
		            foundFirstOne = true;
		        }
		        else if(foundFirstOne){
		            int j = i;
		            int count = 0;
		            while(j < size && s.charAt(j) == '0'){
		                count++;
		                j++;
		            }
		            if(j < size && s.charAt(j) == '1'){
		                total += count;
		            }
		            i = j - 1;
		        }
		    }
		    System.out.println(total);
		}
	}
}
