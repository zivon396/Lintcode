public class Solution {
    /**
     * @param num: An integer
     * @return: an integer array
     */
    public List<Integer> primeFactorization(int num) {
        // write your code here
        List<Integer> res = new ArrayList<>();
        if (num < 1){
            return res;
        }
        
        for (int i = 2; i <= Math.sqrt(num); i++){
            while (num % i == 0){
                res.add(i);
                num /= i;
            }
        }
        if (num != 1){
            res.add(num);
        }
        
        return res;
    }
}