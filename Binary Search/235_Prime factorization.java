// 最后记得加上 num, e.g. 6 = [2, 3]
// 注: 大于一个数的根号的质因数最多只有一个!!!!!!
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
