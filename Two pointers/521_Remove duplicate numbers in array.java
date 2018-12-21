public class Solution {
    /**
     * @param nums: an array of integers
     * @return: the number of unique integers
     */
    public int deduplication(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0){
            return 0;
        }
        Map<Integer, Integer> hash = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            hash.put(nums[i], 1);
        }
        
        int index = 0;
        for (Integer num: hash.keySet()){
            nums[index++] = num;
        }
        
        return index;
    }
}