public class Solution {
    /**
     * @param A: An array of integers
     * @return: A long integer
     */
    public long permutationIndexII(int[] A) {
        int n = A.length;
        long sum = 1;
        long factorial = 1;
        long repeat = 1;
        HashMap<Integer, Integer> num = new HashMap<>();
        for (int i = n - 1; i >= 0; i--) {
            if (num.containsKey(A[i])) {
    	 	   num.put(A[i], num.get(A[i]) + 1);
    	    }
    	    else {
    	 	   num.put(A[i], 1);
    	    }
            if(num.get(A[i]) > 1) {
                // 重复部分的阶乘
                repeat *= num.get(A[i]);
            }

            // 可选取的比当前元素小的元素数目
            int smaller = 0;
            for (int j = i + 1; j < n; j++) {
                if (A[j] < A[i]) {
                    smaller++;
                } 
            }
            sum += smaller * factorial / repeat;
            factorial *= (n - i);
        }
        
        return sum;
    }
}
