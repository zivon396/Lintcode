public class Solution {
    /**
     * @param digits: a number represented as an array of digits
     * @return: the result
     */
    public int[] plusOne(int[] digits) {
        // write your code here
        if (digits == null || digits.length == 0){
            return digits;
        }
        int carry = 1;
        int res = 0;
        for (int i = digits.length - 1; i >= 0; i--){
            res = (digits[i] + carry) % 10;
            carry = (digits[i] + carry) / 10;
            digits[i] = res;
        }

        if (carry == 1){
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            for (int i = 0; i < digits.length; i++){
                result[i + 1] = digits[i];
            }

            return result;
        }
        else {
            return digits;
        }
    }
}
