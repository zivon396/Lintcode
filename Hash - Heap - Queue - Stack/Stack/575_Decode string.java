// 一个 stack 就可以
// push String 就行, 不需要 char
public class Solution {
    /**
     * @param s: an expression includes numbers, letters and brackets
     * @return: a string
     */
    public String expressionExpand(String s) {
        // write your code here
        String res = "";
        if (s == null || s.length() == 0){
            return res;
        }
        
        Stack<String> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) != ']'){
                stack.push(s.substring(i, i + 1));
            } else {
                String str = "";
                String num = "";
                while (!stack.peek().equals("[")){
                    str = stack.pop() + str;
                }
                stack.pop();
                while (!stack.isEmpty() && isNum(stack.peek())){
                    num = stack.pop() + num;
                }
                
                int number = Integer.parseInt(num);
                if (number != 0){
                    String string = "";
                    for (int j = 0; j < number; j++) {
                        string += str;
                    }
                    stack.push(string);
                }
            }
        }
        
        while (!stack.isEmpty()){
            res = stack.pop() + res;
        }
        
        return res;
    }
    
    public boolean isNum(String s) {
        if (s.charAt(0) >= '0' && s.charAt(0) <= '9') {
            return true;
        }
        return false;
    }
}

// 原创
public class Solution {
    /**
     * @param s: an expression includes numbers, letters and brackets
     * @return: a string
     */
    public String expressionExpand(String s) {
        // write your code here
        if (s == null || s.length() == 0){
            return s;
        }
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++){
            if (chars[i] != ']'){
                stack.push(chars[i]);
            }
            else {
                String now = "";
                while (!stack.isEmpty() && Character.isLetter(stack.peek())){
                    now = Character.toString(stack.pop()) + now;
                }
                stack.pop();

                String multi = "";
                while (!stack.isEmpty() && Character.isDigit(stack.peek())){
                    multi = Character.toString(stack.pop()) + multi;
                }
                int time = Integer.parseInt(multi);
                String tmp = now;
                for (int j = 0; j < time - 1; j++){
                    now += tmp;
                }
                if (time == 0){
                    now = "";
                }

                for (int j = 0; j < now.length(); j++){
                    stack.push(now.charAt(j));
                }
            }
        }

        String res = "";
        while (!stack.isEmpty()){
            res = Character.toString(stack.pop()) + res;
        }

        return res;
    }
}
