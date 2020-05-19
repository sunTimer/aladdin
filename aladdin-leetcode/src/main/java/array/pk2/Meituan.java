package array.pk2;

public class Meituan {

    public static void main(String[] args) {
        int[][] nums = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        printArray(nums);
    }

    /**
     * 输入：
     * [1,2,3
     * 4,5,6
     * 7,8,9]
     * 输出：
     * 1
     * 2 4
     * 3 5 7
     * 6 8
     * 9
     *
     * @param nums
     */
    public static void printArray(int[][] nums) {
        int r = 0;

        for (int i = 0; i < nums.length; i++) {
            print(nums, 0, i);
        }

        for (int i = r + 1; i < nums.length; i++) {
            print(nums, i, nums.length - 1);
        }
    }

    public static void print(int[][] nums, int baseM, int baseN) {
        int len = nums.length;
        while (baseM < len && baseN >= 0) {
            System.out.print(nums[baseM++][baseN--] + " ");
        }
        System.out.println();
    }
}
