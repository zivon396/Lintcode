// 统计出每一位数字最后出现的位置, 然后从最高位开始, 对于每一位尝试寻找一个在它右边的最大的数, 若能找到, 直接交换即可
// 时间复杂度 O(n)
public class Solution {
    /**
     * @param num: a non-negative intege
     * @return: the maximum valued number
     */
    public int maximumSwap(int num) {
        char[] digits = Integer.toString(num).toCharArray();

        int[] buckets = new int[10];
        for (int i = 0; i < digits.length; i++) {
            buckets[digits[i] - '0'] = i;
        }

        for (int i = 0; i < digits.length; i++) {
            for (int k = 9; k > digits[i] - '0'; k--) {
                if (buckets[k] > i) {
                    char tmp = digits[i];
                    digits[i] = digits[buckets[k]];
                    digits[buckets[k]] = tmp;
                    return Integer.valueOf(new String(digits));
                }
            }
        }

        return num;
    }
}

// Monotonic Stack
// 单调栈应用面 +1: 寻找 index 最小/大的, 右边存在比它大的数的数
public class Solution {
    public int maximumSwap(int num) {
        String strNum = String.valueOf(num);
        char[] digits = strNum.toCharArray();
        
        Stack<Integer> stack = new Stack<>();
        int left = digits.length;
        // find left index for swap
        for (int i = 0; i < digits.length; i++) {
            while (!stack.isEmpty() && digits[stack.peek()] < digits[i]) {
                int index = stack.pop();
                left = Math.min(left, index);
            }
            stack.push(i);
        }
        
        // max swap exists
        if (left < digits.length) {
            int right = -1;
            char max = digits[left];
            // find right index for swap
            for (int i = left; i < digits.length; i++) {
                if (digits[i] >= max) {
                    right = i;
                    max = digits[i];
                }
            }
            
            swap(digits, left, right);
        }
        
        return Integer.parseInt(new String(digits));
    }
    
    private void swap(char[] digits, int i, int j) {
        char temp = digits[i];
        digits[i] = digits[j];
        digits[j] = temp;
    }
}
