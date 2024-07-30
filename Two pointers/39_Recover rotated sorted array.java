public class Solution {
    /**
     * @param nums: The rotated sorted array
     * @return: The recovered sorted array
     */
    private void reverse(List<Integer> nums, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            int temp = nums.get(i);
            nums.set(i, nums.get(j));
            nums.set(j, temp);
        }
    }

    public void recoverRotatedSortedArray(List<Integer> nums) {
        for (int index = 0; index < nums.size() - 1; index++) {
            // 找到第一个比后面的数大的数，以[4,5,1,2,3]为例，找到5，翻转[4,5]得到[5,4]，翻转[1,2,3]得到[3,2,1]
            // 最后翻转[5,4,3,2,1]得到[1,2,3,4,5]
            if (nums.get(index) > nums.get(index + 1)) {
                reverse(nums, 0, index);
                reverse(nums, index + 1, nums.size() - 1);
                reverse(nums, 0, nums.size() - 1);
                return;
            }
        }
    }
}
