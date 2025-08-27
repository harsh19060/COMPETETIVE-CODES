// Mixing Liquids
// Chef is now making orange juice! He has 
// A
// A units of orange syrup, and 
// B
// B units of water.

// To make orange juice, Chef must mix the orange syrup and the water in exactly a 
// 1
// :
// 2
// 1:2 ratio, otherwise the recipe is spoilt. Further, Chef can only use integer amounts of liquids, because his measuring cylinder cannot accurately measure fractional quantities.
// Chef wants to make as much orange juice as possible. How much will he be able to make? The total quantity of orange juice is the sum of units of orange syrup and water.

import java.util.Scanner;

public class mixingliq {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int out = 0;
        while (t-- > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            out = Math.min(a, b / 2);

            System.out.println(out * 3);
        }

    }
}
