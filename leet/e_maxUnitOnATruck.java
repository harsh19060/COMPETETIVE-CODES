// You are assigned to put some amount of boxes onto one truck. You are given a 2D array boxTypes, where boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi]:

// numberOfBoxesi is the number of boxes of type i.
// numberOfUnitsPerBoxi is the number of units in each box of the type i.
// You are also given an integer truckSize, which is the maximum number of boxes that can be put on the truck. You can choose any boxes to put on the truck as long as the number of boxes does not exceed truckSize.

// Return the maximum total number of units that can be put on the truck.

class e_maxUnitOnATruck {
    
    public int maximumUnits(int[][] boxTypes, int truckSize) {
          Arrays.sort(boxTypes, (a, b) -> Integer.compare(b[1], a[1]));
          int currsize = 0 ;
          int i =0 ;
          int out =0 ;
          while(currsize < truckSize && i < boxTypes.length )
          {
            int num = boxTypes [i][0];
            int unit = boxTypes [i][1];

            if(num <= truckSize-currsize)
            {
                currsize+=num;
                out+= (num*unit);
                i++;
            }
            else{
                int remain = truckSize - currsize;
                out += (remain * unit);
                currsize = truckSize;

            }

          }
return out;
    }
}