// 记录 prefix, 然后按 prefix 排序. 距离 0 最近的 subarray sum 一定是某两个相邻的 prefix 之差.
class Pair {
    int sum;
    int index;
    public Pair(int s, int i) {
        sum = s;
        index = i;
    }
}

public class Solution {
    /*
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public int[] subarraySumClosest(int[] nums) {
        // write your code here
        int[] res = new int[2];
        if (nums == null || nums.length == 0) {
            return res;
        } 
        
        int len = nums.length;
        if(len == 1) {
            res[0] = res[1] = 0;
            return res;
        }
        Pair[] sums = new Pair[len+1];
        int prev = 0;
        sums[0] = new Pair(0, 0);
        for (int i = 1; i <= len; i++) {
            sums[i] = new Pair(prev + nums[i-1], i);
            prev = sums[i].sum;
        }
        Arrays.sort(sums, new Comparator<Pair>() {
           public int compare(Pair a, Pair b) {
               return a.sum - b.sum;
           }
        });
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= len; i++) {
            if (ans > sums[i].sum - sums[i-1].sum) {
                ans = sums[i].sum - sums[i-1].sum;
                int[] temp = new int[]{sums[i].index - 1, sums[i - 1].index - 1};
                Arrays.sort(temp);
                res[0] = temp[0] + 1;
                res[1] = temp[1];
            }
        }
        
        return res;
    }
}

// 上面的更好 (new Pair[len + 1])
class Pair {
    public int index;
    public int sum;
    public Pair (int index, int sum){
        this.index = index;
        this.sum = sum;
    }
}

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    Comparator<Pair> comparator = new Comparator<Pair>(){
        public int compare (Pair left, Pair right){
            return left.sum - right.sum;
        }
    };

    public int[] subarraySumClosest(int[] nums) {
        // write your code here
        int[] res = new int[2];
        int closest = Integer.MAX_VALUE;
        if (nums == null || nums.length == 0){
            return res;
        }
        int n = nums.length;
        Pair[] presums = new Pair[n];
        int sum = 0;
        for (int i = 0; i < n; i++){
            sum += nums[i];
            presums[i] = new Pair(i, sum);
            if (Math.abs(sum) < closest){
                res[0] = 0;
                res[1] = i;
                closest = Math.abs(sum);
            }
        }

        Arrays.sort(presums, comparator);
        for (int i = 1; i < n; i++){
            int diff = presums[i].sum - presums[i - 1].sum;
            if (diff < closest){
                res[0] = presums[i].index;
                res[1] = presums[i - 1].index;
                Arrays.sort(res);
                res[0]++;
                closest = diff;
            }
        }

        return res;
    }
}
