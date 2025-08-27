// 26. Remove Duplicates from Sorted Array
// Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same. Then return the number of unique elements in nums.
// Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:

// Change the array nums such that the first k elements of nums contain the unique elements in the order they were present in nums initially. The remaining elements of nums are not important as well as the size of nums.
// Return k.
// import java.util.*;
// class e_removeDuplicates {
//     int newsize;
//      public void remove_ele(int arr[], int index){
//         for(int i = index ; i <arr.length-1 ; i++ ){
//             arr[i] = arr[i+1];
//         }
//         newsize --;
//     }

//     public int removeDuplicates(int[] nums) {
//       int size = nums.length;
//       newsize = size;
//       int i=0 , j = 1;
//       while (j<size) {
//         if (nums[i]!= nums[j]) {
//             i++;
//             j++;
//         }else{
//             remove_ele(nums,i);
//         }
//       }
//       return newsize;

//         }
//     }

// import java.util.*;

// class e_removeDuplicates {

//     public int removeDuplicates(int[] nums) {
//         int size = nums.length;
//         int newsize = 1;
//         int i = 0;
//         while (j < size) {
//             if (nums[i] == nums[j]) {
//                 j++;
//             } else {
//                 i++ ;
//                 nums[i] = nums[j];
//                 j++;
//                 newsize++;
//             }
//         }
//         return newsize;

//     }
// }



import java.util.*;

class e_removeDuplicates {

    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j]!= nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i+1;

    }
}
