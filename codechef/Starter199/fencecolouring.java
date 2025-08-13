//! FENCECOL //!resoleve
import java.util.*;

class fencecolouring
{
    public static void main(String[] args) throws java.lang.Exception
    {
        int T;
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        while (T--> 0) {
            int n;
            n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            int count = 0;
            for (int i = 0; i < n; i++)
            {
                if (1 != arr[i]) {
                    count++;
                }
            }
            //global paint
            int time;
            int min = Integer.MAX_VALUE;
            for (int i = 1; i <= n; i++)
            {
                time = 0;
                time++;
                for (int j = 0; j < n; j++)
                {
                    if(arr[j]!= i)
                    {
                        time ++;
                    }
                }
                if(time < min ){
                    min = time ;
                }
            }
            
            
            int result = min < count ? min : count ;
            System.out.println(result);
        }

    }
}