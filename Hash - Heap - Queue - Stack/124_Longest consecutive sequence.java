public class Solution {
    /**
     * @param num: A list of integers
     * @return: An integer
     */
    public int longestConsecutive(int[] nums) {
        // write your code here
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++){
            set.add(nums[i]);
        }
        
        int max = 0;
        for (int i = 0; i < nums.length; i++){
            set.remove(nums[i]);
            
            int down = nums[i] - 1;
            while (set.contains(down)){
                set.remove(down);
                down--;
            }
            
            int up = nums[i] + 1;
            while (set.contains(up)){
                set.remove(up);
                up++;
            }
            
            max = Math.max(max, up - down - 1);
        }
        
        return max;
    }
}