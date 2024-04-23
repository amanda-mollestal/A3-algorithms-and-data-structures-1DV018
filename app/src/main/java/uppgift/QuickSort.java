package uppgift;

import java.util.List;

public class QuickSort {

    private void swap(List<Integer> list, int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    private int medianOfThree(List<Integer> list, int low, int high) {
        int mid = low + (high - low) / 2;
        if (list.get(mid) < list.get(low)) {
            swap(list, low, mid);
        }
        if (list.get(high) < list.get(low)) {
            swap(list, low, high);
        }
        if (list.get(high) < list.get(mid)) {
            swap(list, mid, high);
        }
        return list.get(mid);
    }

    private int partition(List<Integer> list, int low, int high) {
        int pivot = medianOfThree(list, low, high);
        int i = low, j = high + 1;

        while (true) {
            // Increment i until an element >= pivot is found
            while (list.get(++i) < pivot) {
                if (i == high)
                    break;
            }

            // Decrement j until an element <= pivot is found
            while (pivot < list.get(--j)) {
                if (j == low)
                    break;
            }

            // Check if pointers cross
            if (i >= j)
                break;

            swap(list, i, j);
        }

        swap(list, low, j); // Place pivot in the correct slot
        return j;
    }

    private void insertionSort(List<Integer> list, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int key = list.get(i);
            int j = i - 1;
            while (j >= low && list.get(j) > key) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    private void quickSort(List<Integer> list, int low, int high, int depth) {
        if (low < high) {
            if (depth == 0) {
                insertionSort(list, low, high);
            } else {
                int pivotIndex = partition(list, low, high);
                quickSort(list, low, pivotIndex, depth - 1);
                quickSort(list, pivotIndex + 1, high, depth - 1);
            }
        }
    }

    public void sort(List<Integer> list, int depth) {
        if (list == null || list.size() <= 1) {
            return;
        }
        quickSort(list, 0, list.size() - 1, depth);
    }
}