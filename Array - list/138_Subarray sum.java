public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number
     *          and the index of the last number
     */

    public ArrayList<Integer> subarraySum(int[] nums) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
       
        // 初始化
        hash.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            // 累加前缀和
            sum += nums[i];
          
            // 前缀和曾经出现，即这个区间的和为0
            if (hash.containsKey(sum)) {
                result.add(hash.get(sum) + 1);
                result.add(i);

                break;
            }
            
            //前缀和第一次出现，存入hash
            hash.put(sum, i);
        }

        return result;
    }
}
