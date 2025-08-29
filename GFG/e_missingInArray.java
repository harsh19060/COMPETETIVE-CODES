// Missing in Array
// Difficulty: EasyAccuracy: 29.59%Submissions: 1.5MPoints: 2Average Time: 15m
// You are given an array arr[] of size n - 1 that contains distinct integers in the range from 1 to n (inclusive). This array represents a permutation of the integers from 1 to n with one element missing. Your task is to identify and return the missing element.


// //! approach 1 O(n2)
// class Solution {
//     int missingNum(int arr[]) 
//     {
//        for(int i = 1 ; i<= arr.length+1 ; i++){
//            boolean tt = false;
//            for(int nums : arr)
//             {
//                if( nums == i)
//                {
//                    tt = true ;
//                    break;
//                 }
                
//             }
//             if(tt == false)
//             {  
//                 return i;
//             }
//        }
//     return -1;
//     }
    
// }



//! approavh 2 sum formula approach 
//? fidnd the sum if all the numbers in range and sunbreact the sum of all the elements of array from it 
class e_missingInArray {
    int missingNum(int arr[]) 
    {
        int n = arr.length;
        long sum = (long)(n+1)*(n+2)/2;
        long arrSum=0;
        for(int nums:arr){
            arrSum+=nums;
        }
        return (int)(sum- arrSum);
    }
    
}