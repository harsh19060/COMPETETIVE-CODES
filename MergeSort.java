public class MergeSort {

    public static void mergesort(int[] arr, int start, int end) {
        if (start >= end) {   // cleaner base condition
            return;
        }

        int mid = start + (end - start) / 2;
        mergesort(arr, start, mid);
        mergesort(arr, mid + 1, end);
        merge(arr, start, mid, end);
    }

    public static void merge(int[] arr, int si, int mi, int ei) {
        int[] temp = new int[ei - si + 1];
        int i = si, j = mi + 1, k = 0;

        while (i <= mi && j <= ei) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mi) {
            temp[k++] = arr[i++];
        }
        while (j <= ei) {
            temp[k++] = arr[j++];
        }

        // copy temp back to original array
        for (int l = 0, o = si; l < temp.length; l++, o++) {
            arr[o] = temp[l];
        }
    }

    public static void printarr(int[] arr) {
        for (int x : arr) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = { 2, 1, 2, 3, 42, 34, 23, 4, 11, 3, 9, 0 };
        mergesort(arr, 0, arr.length - 1);
        printarr(arr);
    }
}
