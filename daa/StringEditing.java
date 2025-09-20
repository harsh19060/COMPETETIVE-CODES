//! CODE FOR STRING EDITIONG (DP)
// import java.util.*;

// class node {
//     int num;
//     char ch;

//     public node(int n, char v) {
//         this.ch = v;
//         this.num = n;
//     }
// }

// public class StringEditing {

//     void Editing(String A, String B, node[][] cost) {
//         int temp = 0, del, upd, ins;
//         char c;
//         cost[0][0] = new node(0, 'x');
//         for (int i = 1; i <= A.length(); i++) {
//             cost[i][0] = new node(cost[i - 1][0].num + 1, 'd');
//         }
//         for (int i = 1; i <= B.length(); i++) {
//             cost[0][i] = new node(cost[0][i - 1].num + 1, 'i');
//         }
//         for (int i = 1; i <= A.length(); i++) {
//             for (int j = 1; j <= B.length(); j++) {

//                 if (A.charAt(i - 1) != B.charAt(j - 1)) {
//                     // temp = Math.min(cost[i-1][j].num+1, cost[i][j-1].num+1);
//                     // temp = Math.min(cost[i-1][j-1].num+2, temp);
//                     // c = cost[i-1][j].num+1 < cost[i][j-1].num+1 ? 'd' : 'i';
//                     // c = cost[i-1][j-1].num+2 == temp ? 'u' : c;
//                     del = cost[i - 1][j].num + 1;
//                     ins = cost[i][j - 1].num + 1;
//                     upd = cost[i - 1][j - 1].num + 2;
//                     if (del <= ins) {
//                         temp = del;
//                         c = 'd';
//                     } else {
//                         temp = ins;
//                         c = 'i';
//                     }
//                     if (temp > upd) {
//                         c = 'u';
//                         temp = upd;
//                     }

//                 } else {
//                     temp = cost[i - 1][j - 1].num;
//                     c = 'm';
//                 }
//                 cost[i][j] = new node(temp, c);
//             }

//         }

//     }

//     void Arrprint(node[][] cost) {
//         for (int i = 0; i < cost.length; i++) {
//             for (int j = 0; j < cost[0].length; j++) {

//                 System.out.print(cost[i][j].num + " " + cost[i][j].ch + "|");

//             }
//             System.out.println("");
//             System.out.println("------------------------------");

//         }
//     }

//     public static void main(String[] args) {
//         StringEditing STE = new StringEditing();
//         Scanner sc = new Scanner(System.in);
//         String s = sc.nextLine(); // jisske similar kana l
//         String ss = sc.nextLine(); // other one
//         node[][] cost = new node[s.length() + 1][ss.length() + 1];
//         STE.Editing(s, ss, cost);
//         System.out.println("cost = " + cost[s.length()][ss.length()].num);
//         STE.Arrprint(cost);

//     }

// }





//! IMPROVED VARIABLE NAMES ..........
import java.util.*;

class Cell {
    int cost;
    char operation;

    public Cell(int cost, char operation) {
        this.cost = cost;
        this.operation = operation;
    }
}

public class StringEditing {

    void computeEditDistance(String source, String target, Cell[][] dp) {
        int currentCost, deleteCost, insertCost, updateCost;
        char chosenOp;

        dp[0][0] = new Cell(0, 'x'); // starting point

        // Fill first column (deletions)
        for (int i = 1; i <= source.length(); i++) {
            dp[i][0] = new Cell(dp[i - 1][0].cost + 1, 'd');
        }

        // Fill first row (insertions)
        for (int j = 1; j <= target.length(); j++) {
            dp[0][j] = new Cell(dp[0][j - 1].cost + 1, 'i');
        }

        // Fill the rest of the matrix
        for (int i = 1; i <= source.length(); i++) {
            for (int j = 1; j <= target.length(); j++) {

                if (source.charAt(i - 1) != target.charAt(j - 1)) {
                    deleteCost = dp[i - 1][j].cost + 1;
                    insertCost = dp[i][j - 1].cost + 1;
                    updateCost = dp[i - 1][j - 1].cost + 2;

                    if (deleteCost <= insertCost) {
                        currentCost = deleteCost;
                        chosenOp = 'd';
                    } else {
                        currentCost = insertCost;
                        chosenOp = 'i';
                    }

                    if (currentCost > updateCost) {
                        currentCost = updateCost;
                        chosenOp = 'u';
                    }

                } else {
                    currentCost = dp[i - 1][j - 1].cost;
                    chosenOp = 'm'; // match
                }

                dp[i][j] = new Cell(currentCost, chosenOp);
            }
        }
    }

    void printDPMatrix(Cell[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j].cost + "" + dp[i][j].operation + " | ");
            }
            System.out.println();
            System.out.println("------------------------------------");
        }
    }

    public static void main(String[] args) {
        StringEditing editor = new StringEditing();
        Scanner sc = new Scanner(System.in);

        String source = sc.nextLine();
        String target = sc.nextLine();

        Cell[][] dp = new Cell[source.length() + 1][target.length() + 1];

        editor.computeEditDistance(source, target, dp);
        System.out.println("Minimum Cost = " + dp[source.length()][target.length()].cost);
        editor.printDPMatrix(dp);
    }
}
