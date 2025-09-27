
import java.util.*;
class root {
    int num;
    char dir;

    public root() {
        this.num = 0;
        this.dir = 'x';
    }
}

public class LCS {
       static int count = 0;
    void LCSS(String a, String b, root[][] arr) {
        for (int i = 0; i <= a.length(); i++) {
            arr[i][0].num = 0;
            arr[i][0].dir = 'H';
        }
        for (int j = 0; j <= b.length(); j++) {
            arr[0][j].num = 0;
            arr[0][j].dir = 'H';
        }


        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) != b.charAt(j - 1)) {
                    if (arr[i - 1][j].num >= arr[i][j - 1].num) {
                        arr[i][j].num = arr[i - 1][j].num;
                        arr[i][j].dir = 'U';
                    } else {
                        arr[i][j].num = arr[i][j - 1].num;
                        arr[i][j].dir = 'S';
                    }
                } else {
                    arr[i][j].dir = 'D';
                    arr[i][j].num = arr[i - 1][j - 1].num + 1;
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
            count ++;
        } else if (arr[l][m].dir == 'U') {
            printlcs(arr, a, l - 1, m);
        } else if (arr[l][m].dir == 'S') {
            printlcs(arr, a, l, m - 1);


        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LCS cc = new LCS();


        String a = "AGCCCTAAGGGCTACCTAGCTT";
        String b = "GACAGCCTACAAGCATTAGCTTG";
        root[][] arr = new root[a.length() + 1][b.length() + 1];
        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                arr[i][j] = new root();
            }
        }


        cc.LCSS(a, b, arr);
        cc.printlcs(arr, a, a.length(), b.length());
        System.out.println("\n"+count);
        sc.close();
    }
}

