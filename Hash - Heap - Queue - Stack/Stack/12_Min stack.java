// 两个 stack, 一个正常, 另一个用于记录当前最小值, 保持两个 stack 同时 push 和 pop.
public class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> min_stack;
    public MinStack() {
        // do intialization if necessary
        stack = new Stack<Integer>();
        min_stack = new Stack<Integer>();
    }

    /*
     * @param number: An integer
     * @return: nothing
     */
    public void push(int number) {
        // write your code here
        stack.push(number);
        if (min_stack.isEmpty()){
            min_stack.push(number);
        } else {
            min_stack.push(Math.min(number, min_stack.peek()));
        }
    }

    /*
     * @return: An integer
     */
    public int pop() {
        // write your code here
        min_stack.pop();
        return stack.pop();
    }

    /*
     * @return: An integer
     */
    public int min() {
        // write your code here
        return min_stack.peek();
    }
}
