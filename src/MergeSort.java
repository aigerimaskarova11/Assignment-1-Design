import java.util.Arrays;

public class MergeSort {

    private static final int INSERTION_SORT_THRESHOLD = 16;

    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int[] buffer = new int[arr.length];
        mergeSort(arr, buffer, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int[] buffer, int left, int right) {
        if (right - left < INSERTION_SORT_THRESHOLD) {
            insertionSort(arr, left, right);
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSort(arr, buffer, left, mid);
        mergeSort(arr, buffer, mid + 1, right);
        merge(arr, buffer, left, mid, right);
    }

    private static void merge(int[] arr, int[] buffer, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            buffer[i] = arr[i];
        }

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (buffer[i] <= buffer[j]) {
                arr[k++] = buffer[i++];
            } else {
                arr[k++] = buffer[j++];
            }
        }

        while (i <= mid) {
            arr[k++] = buffer[i++];
        }
    }

    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 5, 6, 7, 4, 8, 0};
        System.out.println("before sorting: " + Arrays.toString(arr));
        MergeSort.sort(arr);
        System.out.println("after sorting: " + Arrays.toString(arr));
    }
}
