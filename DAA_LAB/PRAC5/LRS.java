import java.util.*;


class root {
    int num;
    char dir;


    public root() {
        this.num = 0;
        this.dir = 'x';
    }
}


public class LRS {


    void LCSS(String a, root[][] arr) {
        int n = a.length();


        for (int i = 0; i <= n; i++) {
            arr[i][0].num = 0;
            arr[i][0].dir = 'H';
        }
        for (int j = 0; j <= n; j++) {
            arr[0][j].num = 0;
            arr[0][j].dir = 'H';
        }


        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (a.charAt(i - 1) == a.charAt(j - 1) && i != j) {
                    arr[i][j].num = arr[i - 1][j - 1].num + 1;
                    arr[i][j].dir = 'D';
                } else {
                    if (arr[i - 1][j].num >= arr[i][j - 1].num) {
                        arr[i][j].num = arr[i - 1][j].num;
                        arr[i][j].dir = 'U';
                    } else {
                        arr[i][j].num = arr[i][j - 1].num;
                        arr[i][j].dir = 'S';
                    }
                }
            }
        }
    }


    void printlcs(root[][] arr, String a, int l, int m) {
        if (l == 0 || m == 0) {
            return;
        }
        if (arr[l][m].dir == 'D') {
            printlcs(arr, a, l - 1, m - 1);
            System.out.print(a.charAt(l - 1));
        } else if (arr[l][m].dir == 'U') {
            printlcs(arr, a, l - 1, m);
        } else if (arr[l][m].dir == 'S') {
            printlcs(arr, a, l, m - 1);
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LRS cc = new LRS();


        System.out.println("Enter the string:");
        String a = sc.nextLine();


        root[][] arr = new root[a.length() + 1][a.length() + 1];
        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= a.length(); j++) {
                arr[i][j] = new root();
            }
        }


        cc.LCSS(a, arr);
        System.out.print("Longest Repeating Subsequence: ");
        cc.printlcs(arr, a, a.length(), a.length());
        System.out.println();


        sc.close();   }
    }
