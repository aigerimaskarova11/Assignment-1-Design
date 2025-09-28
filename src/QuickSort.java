import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    private static final Random RAND = new Random();

    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int left, int right) {
        while (left < right) {
            int pivotIndex = partition(arr, left, right);
            if (pivotIndex - left < right - pivotIndex) {
                quickSort(arr, left, pivotIndex - 1);
                left = pivotIndex + 1;
            } else {
                quickSort(arr, pivotIndex + 1, right);
                right = pivotIndex - 1;
            }
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int pivotIndex = left + RAND.nextInt(right - left + 1);
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < pivotValue) {
                swap(arr, i, storeIndex);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, right);
        return storeIndex;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 1, 5, 6, 7, 11, 8, 0};
        System.out.println("before sorting:" + Arrays.toString(arr));
        QuickSort.sort(arr);
        System.out.println("after sorting " + Arrays.toString(arr));
    }
}