import java.util.*;

class Solution {
    public long maxmul(int[] nums) {
        Map<Integer, Integer> maskMap = new HashMap<>();

        for (int num : nums) {
            int mask = num;
            maskMap.put(mask, Math.max(maskMap.getOrDefault(mask, 0), num));
        }

        long maxmul = 0;
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(maskMap.entrySet());
        for (int i = 0; i < entries.size(); i++) {
            for (int j = i + 1; j < entries.size(); j++) {
                int mask1 = entries.get(i).getKey();
                int val1  = entries.get(i).getValue();
                int mask2 = entries.get(j).getKey();
                int val2  = entries.get(j).getValue();

                if ((mask1 & mask2) == 0) { 
                    maxmul = Math.max(maxmul, (long) val1 * val2);
                }
            }
        }
        return maxmul;
    }
}
