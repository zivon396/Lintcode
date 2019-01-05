public Solution {
    public int oddEven (int[] nums){
        if (nums == null || nums.length == 0){
            return 0;
        }
        int n = nums.length;
        if (n == 1){
            return 1;
        }

        TreeMap<Integer, Integer> hash = new TreeMap<>();
        hash.put(nums[n - 1], n - 1);
        
        int[] odd = new int[n];
        int[] even = new int[n];
        odd[n - 1] = 1;
        even[n - 1] = 1;

        int count = 1;
        for (int i = n - 2; i >= 0; i--){
            hash.put(nums[i], i);
            if (hash.lowerKey(nums) != null && odd[hash.get(hash.lowerKey(nums)]) == 1){
                even[i] = 1;
            } else{
                even[i] = 0;
            }

            if (hash.higherKey(nums) != null && even[hash.get(hash.higherKey(nums)]) == 1){
                odd[i] = 1;
                count++;
            } else{
                odd[i] = 0;
            }
        }

        return count;
    }
}