// stack 1 负责输出, stack 2 负责接收
// 因为要实现的是 queue -> 最早进去的最早出, 后进去的可以后出, 所以可以等 stack 1 空了再把 stack 2 里的移过去
public class MyQueue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public MyQueue() {
       stack1 = new Stack<Integer>();
       stack2 = new Stack<Integer>();
    }
    
    private void stack2ToStack1(){
        while(! stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
    }
    
    public void push(int element) {
        stack2.push(element);
    }

    public int pop() {
        if(stack1.isEmpty()){
            this.stack2ToStack1();
        }
        return stack1.pop();
    }

    public int top() {
        if(stack1.isEmpty()){
            this.stack2ToStack1();
        }
        return stack1.peek();
    }
}
