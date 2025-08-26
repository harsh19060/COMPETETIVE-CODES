import java.util.*;

class que3 {
    static class DSU {
        Map<String, String> parent = new HashMap<>();
        
        String find(String x) {
            if (!parent.containsKey(x)) parent.put(x, x);
            if (!parent.get(x).equals(x))
                parent.put(x, find(parent.get(x))); // path compression
            return parent.get(x);
        }
        
        void union(String a, String b) {
            String rootA = find(a), rootB = find(b);
            if (!rootA.equals(rootB)) {
                parent.put(rootA, rootB);
            }
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        DSU dsu = new DSU();
        Map<String, String> emailToName = new HashMap<>();

        for (List<String> acc : accounts) {
            String name = acc.get(0);
            String firstEmail = acc.get(1);
            for (int i = 1; i < acc.size(); i++) {
                dsu.union(firstEmail, acc.get(i));
                emailToName.put(acc.get(i), name);
            }
        }

        Map<String, TreeSet<String>> groups = new HashMap<>();
        for (String email : emailToName.keySet()) {
            String root = dsu.find(email);
            groups.computeIfAbsent(root, k -> new TreeSet<>()).add(email);
        }

        List<List<String>> result = new ArrayList<>();
        for (String root : groups.keySet()) {
            List<String> merged = new ArrayList<>();
            merged.add(emailToName.get(root)); // add name
            merged.addAll(groups.get(root));   // add sorted emails
            result.add(merged);
        }
        return result;
    }

    public static void main(String[] args) {
        que3 sol = new que3();
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John","johnsmith@mail.com","john_newyork@mail.com"));
        accounts.add(Arrays.asList("John","johnsmith@mail.com","john00@mail.com"));
        accounts.add(Arrays.asList("Mary","mary@mail.com"));
        accounts.add(Arrays.asList("John","johnnybravo@mail.com"));

        System.out.println(sol.accountsMerge(accounts));
    }
}
