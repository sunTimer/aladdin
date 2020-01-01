package offer;

import org.junit.Test;

public class MinNumberInRotateArray {
    public int minNumberInRotateArray(int[] array) {
        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            if (array[left] < array[right]) {
                return array[left];
            }

            int mid = (left + right) >> 1;
            if (array[mid] < array[right]) {
                right = mid;
                continue;
            }
            if (array[mid] > array[left]) {
                left = mid + 1;
                continue;
            }

            left++;
        }
        return array[left];
    }

    @Test
    public void test() {
        int[] a = {10, 11, 12, 13, 14, 15, 1, 2, 3, 4};
        System.out.println(minNumberInRotateArray(a));
    }
}
