import java.lang.*;
import java.util.*;

class chefbakes
{
	public static void main (String[] args) throws java.lang.Exception
	{
		 Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();
        int  count = 0;
        for(int i = 1 ; i <= n ;i++ )
        {
            if(i*x <= y)
            {
                count++;
            }
        }
        
        if(count >= n){
            System.out.println(1); 
        }else{
            int result = (int) Math.ceil((double) n / count);
            System.out.println(result);
        }

	}
}
