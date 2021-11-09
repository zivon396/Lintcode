// rainbow sort
// 注意 nums[left] <= colorMid 要加等号, 不然 colorMid 值会被换到 right 右边.
public class Solution {
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    public void sortColors2(int[] colors, int k) {
        // write your code here
        if (colors == null || colors.length == 0){
            return;
        }
        
        rainbowSort(colors, 0, colors.length - 1, 1, k);
    }
    
    private void rainbowSort (int[] nums,
                              int start,
                              int end,
                              int colorFrom,
                              int colorTo){
        if (colorFrom >= colorTo){
            return;
        }
        if (start >= end){
            return;
        }
        
        int left = start, right = end;
        int colorMid = colorFrom + (colorTo - colorFrom) / 2;
        while (left <= right){
            // 像这种互斥的写法 (<= 和 >), 能保证泾渭分明
            // 而 (< 和 >) 有可能同一个数字在两边出现
            while (left <= right && nums[left] <= colorMid){
                left++;
            }
            while (left <= right && nums[right] > colorMid){
                right--;
            }
            
            if (left <= right){
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left++;
                right--;
            }
        }
        
        rainbowSort(nums, start, right, colorFrom, colorMid);
        rainbowSort(nums, left, end, colorMid + 1, colorTo);
    }
}
