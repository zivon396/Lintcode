// 接龙型动态规划，类似于 Longest Increasing Subsequence 的做法
// 1) 需要记录具体方案 2) O(n^2) 的方法不能通过测试
// 具体的优化在于找上一个接龙的数的时候，不是 for 循环所有比它小的数，而是直接 for 循环它的因子
// 需要两个 map 来分别记录: 1) 最长路径 2) 最长路径对应的 prev
// 不能像 76 一样用 Binary Search, 因为元素之间没有可替代性
public class Solution {
    /**
     * @param nums a set of distinct positive integers
     * @return the largest subset 
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList();
        }
        
        Arrays.sort(nums);
        int n = nums.length;
        HashMap<Integer, Integer> dp = new HashMap();
        HashMap<Integer, Integer> prev = new HashMap();
        
        for (int i = 0; i < n; i++) {
            dp.put(nums[i], 1);
            prev.put(nums[i], -1);
        }
        
        int lastNum = nums[0];
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            for (Integer factor : getFactors(num)) {
                if (!dp.containsKey(factor)) {
                    continue;
                }
                if (dp.get(num) < dp.get(factor) + 1) {
                    dp.put(num, dp.get(factor) + 1);
                    prev.put(num, factor);
                }
            }
            if (dp.get(num) > dp.get(lastNum)) {
                lastNum = num;
            }
        }
        
        return getPath(prev, lastNum);
    }
    
    private List<Integer> getPath(HashMap<Integer, Integer> prev, int lastNum) {
        List<Integer> path = new ArrayList();
        while (lastNum != -1) {
            path.add(lastNum);
            lastNum = prev.get(lastNum);
        }
        Collections.reverse(path);
        return path;
    }
    
    private List<Integer> getFactors(int num) {
        List<Integer> factors = new ArrayList();
        if (num == 1) {
            return factors;
        }
        int factor = 1;
        while (factor * factor <= num) {
            if (num % factor == 0) {
                factors.add(factor);
                if (factor != 1 && num / factor != factor) {
                    factors.add(num / factor);
                }
            }
            factor++;
        }
        return factors;
    }
}
