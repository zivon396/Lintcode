public class Solution {
    /**
     * @param nums1: an array
     * @param nums2: an array
     * @return:  find all the next greater numbers for nums1's elements in the corresponding places of nums2
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // Write your code here
        int[] res = new int[nums1.length];
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> hash = new HashMap<>();
        
        int index = 0;
        for (int i = 0; i < nums2.length; i++){
            if (stack.isEmpty() || nums2[i] <= stack.peek()){
                stack.push(nums2[i]);
            } else {
                hash.put(stack.pop(), nums2[i]);
                i--;
            }
        }
        
        for (int i = 0; i < nums1.length; i++){
            res[i] = hash.containsKey(nums1[i]) ? hash.get(nums1[i]) : -1;
        }
        
        return res;
    }
}