// //! earlist time to fininsh a task
// You are given a 2D integer array tasks where tasks[i] = [si, ti].

// Each [si, ti] in tasks represents a task with start time si that takes ti units of time to finish.

// Return the earliest time at which at least one task is finished.

public class e_earlisttime {
      public int earliestTime(int[][] tasks) {
        int minsum = Integer.MAX_VALUE;
        int sum=0;
        for(int i=0;i < tasks.length; i++){
                minsum =Math.min(minsum,(tasks[i][1]+tasks[i][0]));
        }
        if(minsum>0){
            return minsum;
        }else{
          return 0;   
        }
    }
}
