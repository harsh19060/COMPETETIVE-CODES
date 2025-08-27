
import java.util.Arrays;

public class insertionSort {
    static void insertionS(int arr[]){
        for(int i =1 ; i < arr.length ;i ++ )
        {
            int curr = arr[i] ;
            int prev = i-1;
            //correct posiotion to inset
            while (prev>=0 && arr[prev]>curr) { 
                arr[prev+1]= arr[prev];
                prev -- ;
            }
            // inseriton 
            arr[prev+1] = curr;
        }
    }

     static void printArr(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.err.print(" " + arr[i]);
          
        }  System.out.println();
    }

     public static void main(String[] args) {
        int arr[] = {5,4,1,3,2};
        // insertionS(arr);
        Arrays.sort(arr); //! internal sort of java
        printArr(arr);
    }
}
