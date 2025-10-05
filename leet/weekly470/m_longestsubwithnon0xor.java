package weekly470;

public class m_longestsubwithnon0xor {

    public int longestSubsequence(int[] nums) {
        int n = nums.length;
        if(n==0) return 0;
        int totxor = 0;
        for(int v : nums) totxor ^=v;
        if(totxor!=0) return n ;

        int left = n ;
        int prexor = 0;
        for(int i = 0 ; i < n ; i++){
            prexor^=nums[i];
            if(prexor != 0 ){
                left = i+1;
                break;
            }
        }

        int right = n ;
        int suffxor = 0;
        for(int i = n-1 ; i >= 0 ; i--){
            suffxor^=nums[i];
            if(suffxor != 0 ){
                right = n-i;
                break;
            }
        }

        int remove =  Math.min(left,right);
        if(remove == n )return 0;
        return n - remove;
}}
