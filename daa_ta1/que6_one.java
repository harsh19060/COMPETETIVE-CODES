package daa_ta1;

public class que6_one {
    static double dist(int[] p1, int[] p2) {
        return Math.sqrt(Math.pow(p1[0]-p2[0],2) + Math.pow(p1[1]-p2[1],2));
    }

    public static void main(String[] args) {
        int[] A = {0,0}, B = {2,0}, C = {0,2};
        int[] S = {1,1}; //? example sterner point

        double noSteiner = dist(A,B) + dist(A,C);   //! output ==  4.0
        double withSteiner = dist(A,S) + dist(B,S) + dist(C,S); //! output == 4.242...

        System.out.println("Without Steiner: " + noSteiner);
        System.out.println("With Steiner (trial): " + withSteiner);
    }
}
