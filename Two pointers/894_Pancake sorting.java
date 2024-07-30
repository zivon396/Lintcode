// 每次都将还未排序的数字中最大的数字先交换到第一个, 然后再交换到应在的位置
// 最多交换 2 * N 次
public class Solution {
    /**
     * @param array: an integer array
     * @return: nothing
     */
    public void pancakeSort(int[] array) {
        // Write your code here
        int n = array.length;
        for (int i = n - 1; i > 0; i--) {
            // 执行 n - 1 次, 因为最后剩一个最小的在第一个, 不用处理。
            int Max = 0;
            for (int j = 1; j <= i; j++) {
                if (array[j] > array[Max]) {
                    Max = j;
                }
            }

            if(Max != 0 && Max != i) {
                FlipTool.flip(array, Max);
                FlipTool.flip(array, i);
            }
            else if (Max == 0) {
                FlipTool.flip(array, i);
            }
        }
    }
}
