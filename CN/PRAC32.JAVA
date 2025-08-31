import java.util.*;

public class PRAC {
    static final int WINDOW_SIZE = 4;
    static final int TIMEOUT = 3;

    static class Packet {
        int seq;
        String data;
        Packet(int s, String d) { seq = s; data = d; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of packets: ");
        int n = sc.nextInt();
        sc.nextLine();
        List<Packet> packets = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter data for packet " + i + ": ");
            String d = sc.nextLine();
            packets.add(new Packet(i, d));
        }

        Random rand = new Random();
        int base = 0, nextSeq = 0;
        List<String> received = new ArrayList<>();

        while (base < n) {
            while (nextSeq < base + WINDOW_SIZE && nextSeq < n) {
                System.out.println("Sender: Sending packet " + nextSeq + " [" + packets.get(nextSeq).data + "]");
                nextSeq++;
            }

            boolean ackLost = rand.nextInt(4) == 0; 
            if (ackLost) {
                System.out.println("Receiver: ACK lost for packet " + base);
                System.out.println("Timeout! Retransmitting from packet " + base);
                nextSeq = base;
            } else {
                System.out.println("Receiver: Packet " + base + " received correctly, sending ACK " + base);
                received.add(packets.get(base).data);
                base++;
            }
            System.out.println();
        }

        System.out.println("Final Received Data Stream: " + received);
    }
}
