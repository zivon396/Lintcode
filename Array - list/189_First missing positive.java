// 每次把当前的数换到它应该在的 index 上
// 时间复杂度 O(2n)
// 空间复杂度 O(1)
public class Solution {
    /**
     * @param A: An array of integers
     * @return: An integer
     */
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int n = nums.length;
        
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[i] != i + 1) {
                int tmp = nums[i];
                int index = nums[i] - 1;
                if (nums[index] == tmp) {
                    break;
                }
                nums[i] = nums[index];
                nums[index] = tmp;
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }   
        }

        return n + 1;
    }
}

// 时间复杂度 O(n)
// 空间复杂度 O(n)
public class Solution {
    /**
     * @param a: An array of integers
     * @return: An integer
     */
    public int firstMissingPositive(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0){
            return 1;
        }
        int n = nums.length;
        boolean[] set = new boolean[n];

        for (Integer num :nums){
            if (num <= 0 || num > n){
                continue;
            }
            set[num - 1] = true;
        }

        for (int i = 0; i < n; i++){
            if (!set[i]){
                return i + 1;
            }
        }

        return n + 1;
    }
}

// 时间复杂度 O(n²)
public class Solution {
    /**
     * @param A: An array of integers
     * @return: An integer
     */
    public int firstMissingPositive(int[] A) {
        // i 是要找的最小正整数, 从 1 开始, 最大是数组长度 + 1
        for (int i = 1; i <= A.length + 1; i++) {
            for (int j = 0; j < A.length; j++) {
                if (A[j] == i) break; // 找到了, 跳出内循环, 找下一个最小数
                if (j == A.length - 1 && A[j] != i) // 找到最后一位也不是, 返回 i
                    return i;
            }
        }

        return 1; // 数组为空的情况
    }
}
