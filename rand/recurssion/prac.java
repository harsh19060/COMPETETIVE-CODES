//print numbers form 1 to n
public class prac {
    public static  void numprint(int n ){
        if (n==0) {
            return ;
        }
        numprint(n-1);
        System.out.print(" "+n+" ");
    }
    public static void main(String[] args) {
        numprint(10);
    }
}