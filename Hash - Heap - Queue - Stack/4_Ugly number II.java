// Heap 版本: heap + set -> 时间复杂度为 O(nlogn)
// DP 版本时间复杂度为 O(n)
public class Solution {
    /**
     * @param n: An integer
     * @return: the nth prime number as description.
     */
    private long res;
    
    public int nthUglyNumber(int n) {
        // write your code here
        if (n <= 6){
            return n;
        }
        
        long[] primes = new long[] {2, 3, 5};
        Queue<Long> heap = new PriorityQueue<Long>();
        Set<Long> set = new HashSet<>();
        for (int i = 0; i < 3; i++){
            heap.add(primes[i]);
            set.add(primes[i]);
        }
        
        for (int j = 0; j < n - 1; j++){
            res = heap.poll();
            for (int i = 0; i < 3; i++){
                long next = primes[i] * res;
                if (!set.contains(next)){
                    heap.add(next);
                    set.add(next);
                }
            }
        }
        
        return (int)res;
    }
}
