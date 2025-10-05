
public class firstUpper {

    public static void main(String[] args) {
        String p = "harshvardhan is a good boy";
        StringBuilder bb = new StringBuilder("");
        boolean t = true;
        Character c;
        for (int i = 0; i < p.length(); i++) {
            c = p.charAt(i);
            if (t) {
                bb.append(Character.toUpperCase(c));
                t = false;
                continue;
            }
            bb.append(c);
            if (c.equals(' ')) {
                t = true;
            }
        }
        System.out.println(bb);
    }
}