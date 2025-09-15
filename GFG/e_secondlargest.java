// Second Largest
// Difficulty: EasyAccuracy: 26.72%Submissions: 1.3MPoints: 2Average Time: 15m
// Given an array of positive integers arr[], return the second largest element from the array. If the second largest element doesn't exist then return -1.

// Note: The second largest element should not be equal to the largest element.

import java.util.Arrays;

public class e_secondlargest {
    public int getSecondLargest(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        int largest = arr[n-1] ;
        for(int i = n-2 ; i >= 0 ; i--){
            if(arr[i]!= largest){
                return arr[i];
            }
        }
        return -1;
        
    }
}
