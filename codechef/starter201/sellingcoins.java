// Selling Coins
// Chef has 
// A
// A silver coins and 
// B
// B gold coins.

// He can avail the following 
// 2
// 2 deals (multiple times):

// Sell 
// 1
// 1 silver coin for Rs. 
// 1
// 1.
// Trade 
// 1
// 1 gold coin for 
// 2
// 2 silver coins.

import java.util.Scanner;

public class sellingcoins {
    public static void main(String[] args) throws java.lang.Exception {

        int x, y;
        Scanner sc = new Scanner(System.in);
        x = sc.nextInt();
        y = sc.nextInt();
        System.out.println(x + (y * 2));

    }
}
