package heap;

import org.junit.Test;

/**
 * 有一堆石头，每块石头的重量都是正整数。
 * <p>
 * 每一回合，从中选出两块最重的石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 * <p>
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/last-stone-weight
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _1046 {

    private int len;

    public int lastStoneWeight(int[] nums) {
        len = nums.length - 1;
        int lastNoLeaf = (len - 1) >> 1;
        for (int i = lastNoLeaf; i >= 0; i--) {
            heapify(nums, len, i);
        }

        while (len > 0) {
            int y = extractMax(nums);
            int x = getMax(nums);
            if (x == y) {
                extractMax(nums);
            } else {
                nums[0] = y - x;
                heapify(nums, len, 0);
            }
        }

        return len == -1 ? 0 : getMax(nums);
    }


    public int getMax(int[] nums) {
        return nums[0];
    }

    public int extractMax(int[] nums) {
        int max = nums[0];
        swap(nums, 0, len);
        heapify(nums, --len, 0);
        return max;
    }

    public void heapify(int[] nums, int len, int index) {
        if (index > len) {
            return;
        }

        int l = left(index);
        int r = l + 1;

        int max = index;

        if (l <= len && nums[l] > nums[max]) {
            max = l;
        }
        if (r <= len && nums[r] > nums[max]) {
            max = r;
        }

        swap(nums, index, max);
        if (max != index) {
            heapify(nums, len, max);
        }
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public int left(int i) {
        return (i << 1) + 1;
    }

    @Test
    public void test() {
        int[] nums = {4, 4, 5, 5, 5};
        int i = lastStoneWeight(nums);
        System.out.println(i);
    }
}
