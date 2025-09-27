
public class MaxResourceSubarray {
    static class Result {
        int sum, l, r;
        Result(int s, int i, int j){ sum=s; l=i; r=j; }
    }

    static Result maxUnderConstraint(int[] a, int k){
        int n=a.length, l=0, best=-1, bl=-1, br=-1;
        long sum=0;
        for(int r=0;r<n;r++){
            sum+=a[r];
            while(l<=r && sum>k) sum-=a[l++];
            if(sum<=k && sum>best){ best=(int)sum; bl=l; br=r; }
        }
        return new Result(best, bl, br);
    }

    public static void main(String[] args){
        int[] resources = {1,2,3};
        int constraint = 0;
        Result res = maxUnderConstraint(resources, constraint);
        if(res.sum<0){
            System.out.println("No feasible subarray");
        }else{
            System.out.println("Max sum = " + res.sum );
            System.out.print("Subarray: ");
            for(int i=res.l;i<=res.r;i++) System.out.print(resources[i]+" ");
        }
    }
}
