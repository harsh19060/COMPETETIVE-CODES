import java.util.*;

public class prac3 {
    static String POLY = "1011";
    static String DELIM = "0111110";

    static void xorOperation(char[] dividend, String divisor, int pos) {
        for (int i = 0; i < divisor.length(); i++) {
            dividend[pos + i] = (dividend[pos + i] == divisor.charAt(i)) ? '0' : '1';
        }
    }

    static String computeCRC(String msg) {
        int m = msg.length();
        int n = POLY.length();
        char[] temp = msg.toCharArray();
        for (int i = 0; i <= m - n; i++) {
            if (temp[i] == '1') xorOperation(temp, POLY, i);
        }
        return new String(temp).substring(m - n + 1);
    }

    static String bitStuff(String input) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            sb.append(input.charAt(i));
            if (input.charAt(i) == '1') {
                count++;
                if (count == 5) {
                    sb.append('0');
                    count = 0;
                }
            } else count = 0;
        }
        return sb.toString();
    }

    static String bitDestuff(String input) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            sb.append(input.charAt(i));
            if (input.charAt(i) == '1') {
                count++;
                if (count == 5 && i + 1 < input.length() && input.charAt(i + 1) == '0') {
                    i++;
                    count = 0;
                }
            } else count = 0;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter 8-bit message: ");
        String message = sc.next();
        String msgWithCRC = message + "000";
        String crc = computeCRC(msgWithCRC);
        msgWithCRC = message + crc;
        System.out.println("Message + CRC: " + msgWithCRC);

        String stuffed = bitStuff(msgWithCRC);
        String frame = DELIM + stuffed + DELIM;
        System.out.println("Transmitted Frame: " + frame);

        String received = frame.substring(DELIM.length(), frame.length() - DELIM.length());
        String destuffed = bitDestuff(received);
        System.out.println("Received (after de-stuffing): " + destuffed);

        String check = destuffed + "000";
        String checkCRC = computeCRC(check);
        boolean error = false;
        for (char c : checkCRC.toCharArray()) if (c != '0') error = true;

        if (error) System.out.println("Error detected in received message!");
        else System.out.println("Message received correctly: " + destuffed.substring(0, 8));
    }
}
