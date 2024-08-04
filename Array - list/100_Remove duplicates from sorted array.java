public class Solution {
    /*
     * @param nums: An ineger array
     * @return: An integer
     */
    public int removeDuplicates(int[] nums) {
        // write your code here
        Set<Integer> hash = new HashSet<>();
        int index = 0;
        for (int i = 0; i < nums.length; i++){
            if (hash.contains(nums[i])){
                continue;
            }
            nums[index++] = nums[i];
            hash.add(nums[i]);
        }

        return index;
    }
}
