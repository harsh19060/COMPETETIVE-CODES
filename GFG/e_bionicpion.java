public class e_bionicpion {
     public int findMaximum(int[] arr) {
      int n =  arr.length;
      int low=0,mid,high=n-1;
      while(low<=high){
          mid = low+(high-low)/2;
          if((n==0 || (arr[mid-1] < arr[mid]) )&&(mid == n-1 || arr[mid]>arr[mid+1])){
              return arr[mid];
          }
          
          if(mid<n-1 && arr[mid]<arr[mid+1])
          {
              low= mid+1;
          }else{
              high = mid-1;
          }
      }
      return -1;
   
    }
}
