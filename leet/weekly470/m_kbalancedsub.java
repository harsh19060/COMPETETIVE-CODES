package weekly470;

public class m_kbalancedsub {
   
    public String removeSubstring(String s, int k) {
        String ss = s;
        boolean changed = true;
        while (changed) {
            changed = false;
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (i < ss.length()) {
                if (i + 2 * k <= ss.length()) {
                    boolean valid = true;
                    for (int j = i; j < i + k; j++) {
                        if (ss.charAt(j) != '(') {
                            valid = false;
                            break;
                        }
                    }
                    if (valid) {
                        for (int j = i + k; j < i + 2 * k; j++) {
                            if (ss.charAt(j) != ')') {
                                valid = false;
                                break;
                            }
                        }
                    }
                    if (valid) {
                        changed = true;
                        i += 2 * k;
                        continue;
                    }
                }
                sb.append(ss.charAt(i));
                i++;
            }
            ss = sb.toString();
        }
        return ss;
    }
}

