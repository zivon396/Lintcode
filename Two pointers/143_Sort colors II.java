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
            // 必须用这种互斥的写法 (<= 和 >), 能保证泾渭分明
            // 否则 (< 和 >) 有可能同一个数字在两边出现
            // 进而发生栈溢出
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

// 直接排序也不是不行
public class Solution {
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    public void sortColors2(int[] nums, int k) {
        // write your code here
        if (nums == null || nums.length == 0){
            return;            
        }
        Arrays.sort(nums);

        partition(nums, 0, nums.length - 1);
    }

    private void partition (int[] nums,
                            int start,
                            int end){
        if (start >= end){
            return;
        }

        int left = start, right = end;
        int pivot = nums[start + (end - start) / 2];
        while (left <= right){
            while (left <= right && nums[left] < pivot){
                left++;
            }
            while (left <= right && nums[right] > pivot){
                right--;
            }

            if (left <= right){
                swap(nums, left, right);
                left++;
                right--;
            }
        }

        partition(nums, start, right);
        partition(nums, left, end);
    }

    private void swap (int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
