
//version 1: Pair + two pointers
class Pair {
    public int index, val;
    public Pair (int index, int val){
        this.index = index;
        this.val = val;
    }
}

public class Solution {
    /**
     * @param numbers: An array of Integer
     * @param target: target = numbers[index1] + numbers[index2]
     * @return: [index1, index2] (index1 < index2)
     */
    private Comparator<Pair> pairComparator = new Comparator<Pair>(){
        public int compare (Pair left, Pair right){
            return left.val - right.val;
        }
    };
    
    public int[] twoSum(int[] numbers, int target) {
        // write your code here
        int[] res = {-1, -1};
        Pair[] nums = new Pair[numbers.length];
        for (int i = 0; i < numbers.length; i++){
            nums[i] = new Pair(i, numbers[i]);
        }
        
        Arrays.sort(nums, pairComparator);
        
        int left = 0, right = nums.length - 1;
        while (left < right){
            if (nums[left].val + nums[right].val == target){
                res[0] = nums[left].index < nums[right].index? nums[left].index : nums[right].index;
                res[1] = nums[left].index > nums[right].index? nums[left].index : nums[right].index;
                return res;
            } else if (nums[left].val + nums[right].val < target){
                left++;
            } else {
                right--;
            }
        }
        
        return res;
    }
}

//version 2: HashMap
public class Solution {
    /**
     * @param numbers: An array of Integer
     * @param target: target = numbers[index1] + numbers[index2]
     * @return: [index1, index2] (index1 < index2)
     */
    public int[] twoSum(int[] nums, int target) {
        // write your code here
        int[] res = {-1 ,-1};
        Map<Integer, Integer> hash = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++){
            if (hash.containsKey(nums[i])){
                res[0] = hash.get(nums[i]);
                res[1] = i;
            }
            
            hash.put(target - nums[i], i);
        }
        
        return res;
    }
}