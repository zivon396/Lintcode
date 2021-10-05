// version 1: HashMap
public class Solution {
    /**
     * @param nums: an array of Integer
     * @param target: an integer
     * @return: [index1 + 1, index2 + 1] (index1 < index2)
     */
    public int[] twoSum7(int[] nums, int target) {
        // write your code here
        int[] res = {-1, -1};
        if (nums == null || nums.length == 0){
            return res;
        }
        Map<Integer, Integer> hash = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++){
            if (hash.containsKey(nums[i])){
                // 最新版本题目是返回 value 而不是 index
                res[0] = hash.get(nums[i]) + 1;
                res[1] = i + 1;
                return res;
            }
            hash.put(nums[i] + target, i);
            hash.put(nums[i] - target, i);
        }
        
        return res;
    }
}

// version 2: Same direction two pointers
// 同向指针, while 条件 right < nums.length
class Pair {
    public int index, val;
    public Pair (int index, int val){
        this.index = index;
        this.val = val;
    }
}

public class Solution {
    /**
     * @param nums: an array of Integer
     * @param target: an integer
     * @return: [index1 + 1, index2 + 1] (index1 < index2)
     */
    private Comparator<Pair> pairComparator = new Comparator<Pair>(){
        public int compare (Pair left, Pair right){
            return left.val - right.val;
        }
    };
    
    public int[] twoSum7(int[] nums, int target) {
        // write your code here
        int[] res = {-1, -1};
        if (nums == null || nums.length == 0){
            return res;
        }
        target = Math.abs(target);
        
        Pair[] pairs = new Pair[nums.length];
        for (int i = 0; i < nums.length; i++){
            pairs[i] = new Pair(i, nums[i]);
        }
        Arrays.sort(pairs, pairComparator);
        
        int left = 0, right = 1;
        while (right < nums.length){
            if (left == right){
                right++;
            }
            if (pairs[right].val - pairs[left].val == target){
                res[0] = Math.min(pairs[left].index, pairs[right].index) + 1;
                res[1] = Math.max(pairs[left].index, pairs[right].index) + 1;
                return res;
            } else if (pairs[right].val - pairs[left].val < target){
                right++;
            } else {
                left++;
            }
        }
        
        return res;
    }
}
