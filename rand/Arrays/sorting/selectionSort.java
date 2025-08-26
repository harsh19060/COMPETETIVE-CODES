
public class selectionSort {
    static void SelectionS(int arr[]) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j <= arr.length - 1; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            // swap
            int temp = 0;
            temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
            printArr(arr);

        }
    }

    static void printArr(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.err.print(" " + arr[i]);
          
        }  System.out.println();
    }

     public static void main(String[] args) {
        int arr[] = {5,4,1,3,2};
        SelectionS(arr);
        printArr(arr);
    }

}
