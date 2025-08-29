// Array Duplicates
// Difficulty: EasyAccuracy: 18.95%Submissions: 853K+Points: 2Average Time: 20m
// Given an array arr[] of size n, containing elements from the range 1 to n, and each element appears at most twice, return an array of all the integers that appears twice.

// Note: You can return the elements in any order but the driver code will print them in sorted order.

import java.util.ArrayList;

public class e_arrayDuplicates {
    //byharshvardhan talokar
    //29082025
     public ArrayList<Integer> findDuplicates(int[] arr) {
       int curr = 0;
       int count = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i :arr){
            list.add(i);
        }
        for(Integer intt : list){
            count =  1 ;
            curr =  intt ;
            list.remove(intt);
            while (list.remove(curr)!= -1) {
                count ++ ;
            }
            if (count == 2) {
                list.add(curr);
            }
            
        }
         return list;
    }
}



// this solution is on halt and not corrent requres knowledge of maps and graphs in review