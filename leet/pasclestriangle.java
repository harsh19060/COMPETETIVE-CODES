import java.util.*;

public class pasclestriangle {
    public List<List<Integer>> generate(int numRows) {
        List <List<Integer>> Ptriangle = new ArrayList<>();
        Ptriangle.add(new ArrayList<>());
        Ptriangle.get(0).add(1);
        for(int i = 1 ; i< numRows ; i++){
            List<Integer> row = new ArrayList<>();
            List<Integer> prevRow = Ptriangle.get(i-1);
            row.add(1);
            for(int j = 1 ;j<i;j++){
                row.add(prevRow.get(j-1)+prevRow.get(j));
            }
            row.add(1);
            Ptriangle.add(row);
        }
        return Ptriangle;
        } 

    }
