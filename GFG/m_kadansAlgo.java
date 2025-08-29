

// public class m_kadansAlgo {
//     int maxSubarraySum(int[] arr) {

//         int curr = 0;
//         for (int nums : arr) {
//             if (curr + nums < 0) {
//                 curr=0;
//             } else {
//                 curr+= nums;
//             }
//         }
//         return curr;

//     }
// }




public class m_kadansAlgo {

    int maxSubarraySum(int[] arr) {
        int curr = arr[0], maxSum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            curr = Math.max(curr + arr[i], arr[i]);
            maxSum = Math.max(maxSum, curr);
        }
        return maxSum;
    }

}
