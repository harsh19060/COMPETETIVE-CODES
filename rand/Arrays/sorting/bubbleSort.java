

public class bubbleSort {
    static void bubbleS(int arr[]) {
        for (int turns = 0; turns <= arr.length - 1; turns++) {
            for (int j = 0; j < arr.length - 1 - turns; j++) {
                if (arr[j] > arr[j + 1]) {
                    // swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    static void printArr(int arr[]){
        for (int i = 0; i < arr.length; i++) {
            System.err.print(" "+arr[i]);
        }
    }

    public static void main(String[] args) {
        int arr[] = {1,11,33,0,312,12,444,6,43,77,900};
        bubbleS(arr);
        printArr(arr);
    }
}
