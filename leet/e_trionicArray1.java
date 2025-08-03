// You are given an integer array nums of length n.

// An array is trionic if there exist indices 0 < p < q < n − 1 such that:

// nums[0...p] is strictly increasing,
// nums[p...q] is strictly decreasing,
// nums[q...n − 1] is strictly increasing.
// Return true if nums is trionic, otherwise return false.

// public class e_trionicArray1 {
//     public boolean isTrionic(int[] nums) {
//         int p = -1;int q= -1; int r = 0;
//         for (int i = 0; i < nums.length; i++) {
//             if (nums[i] < nums[i+1]) {
//                 p++;
//             }if (p!= -1 && q == -1 && nums[i]>nums[i+1]) {
//                 q++;
//             }if (q!= -1 && nums[i] < nums[i+1]) {
//                 r++;
//             }
//         }
//         if (p>1 && q>1 && r>1) {
//             return true;
//         }return false;
//     }
// }

class Solution {
    public boolean isTrionic(int[] nums) {
        int n = nums.length;
        if (n < 3)
             return false;

        int i = 1;

        while (i < n && nums[i] > nums[i - 1]) 
            i++;
        if (i == 1 || i == n) 
            return false; 

        int p = i - 1;

        while (i < n && nums[i] < nums[i - 1]) 
            i++;
        if (i == p + 1 || i == n) 
            return false;

        int q = i - 1;

        while (i < n && nums[i] > nums[i - 1]) 
            i++;
        if (i == q + 1 || i != n) 
            return false; 

        return true;
    }
}


