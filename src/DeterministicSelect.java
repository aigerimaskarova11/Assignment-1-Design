import java.util.Arrays;

public class DeterministicSelect {

    public static int select(int[] arr, int k) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException();
        if (k < 1 || k > arr.length) throw new IllegalArgumentException();
        return select(arr, 0, arr.length - 1, k - 1);
    }

    private static int select(int[] arr, int left, int right, int kIndex) {
        while (left <= right) {
            if (left == right) return arr[left];
            int pivotIndex = medianOfMedians(arr, left, right);
            pivotIndex = partition(arr, left, right, pivotIndex);
            if (kIndex == pivotIndex) return arr[kIndex];
            else if (kIndex < pivotIndex) right = pivotIndex - 1;
            else left = pivotIndex + 1;
        }
        throw new RuntimeException();
    }

    private static int medianOfMedians(int[] arr, int left, int right) {
        int n = right - left + 1;
        int numMedians = 0;
        for (int i = left; i <= right; i += 5) {
            int subRight = Math.min(i + 4, right);
            insertionSort(arr, i, subRight);
            int median = i + (subRight - i) / 2;
            swap(arr, left + numMedians, median);
            numMedians++;
        }
        int mid = left + (numMedians - 1) / 2;
        return select(arr, left, left + numMedians - 1, mid);
    }

    private static int partition(int[] arr, int left, int right, int pivotIndex) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int store = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < pivotValue) {
                swap(arr, store, i);
                store++;
            }
        }
        swap(arr, store, right);
        return store;
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

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int[] a = {7,2,1,6,8,9,3,4};
        System.out.println(select(a, 1));
        System.out.println(select(a, 4));
        System.out.println(select(a, 8));
    }
}
