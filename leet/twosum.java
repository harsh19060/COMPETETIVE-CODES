public class twosum {
    public int[] twoSum(int[] nums, int target) {
        int a;
        int b;
        int c;
        int out[] =new int[2];

        for (int i = 0; i < nums.length; i++) {
            a = nums[i];
            b = target - a;
            c=contains(nums,b);
            if (c!=-1) {
                if (i != c) {
                    out [0]=i;
                    out [1]=c;
                    return out;            
                }
            }
        }
    }

    static int contains(int arr[], int  t ){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]== t) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

    }
}