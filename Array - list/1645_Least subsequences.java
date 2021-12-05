public class Solution {
    /**
     * @param arrayIn: The original array.
     * @return: Count the minimum number of subarrays.
     */
    public int LeastSubsequences(List<Integer> arrayIn) {
        // Write your code here.
        // patient sorting
        if (arrayIn.size() == 0) {
            return 0;
        }

        List<Integer> buckets = new ArrayList<>();
        buckets.add(arrayIn.get(0));
        for(int i = 1; i < arrayIn.size(); i++){
            int cur = arrayIn.get(i);
            int position = binary_search(cur, buckets);
            // buckets 里的元素全部小于等于 target
            if (position == buckets.size()){
                buckets.add(cur);
            } else {
                // 从大于的那一个开始
                buckets.set(position, cur);
            }
        }

        return buckets.size();
    }

    // Binary search, 找到 <= target 的个数 (或者说第一个 > target 的 position)
    private int binary_search(int target, List<Integer> buckets){
        int start = 0;
        int end = buckets.size() - 1;

        if (buckets.get(start) > target){
            return start;
        }

        while(start + 1 < end){
            int mid = start + (end - start) / 2;
            if(buckets.get(mid) > target){
                end = mid;
            }
            else{
                start = mid;
            }
        }
        
        if (buckets.get(end) <= target){
            return end + 1;
        }

        return start + 1;
    }
}
