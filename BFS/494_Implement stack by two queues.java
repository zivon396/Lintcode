public class Stack {
    /*
     * @param x: An integer
     * @return: nothing
     */
    private Queue<Integer> q1;
    private Queue<Integer> q2;
    
    public Stack (){
        this.q1 = new LinkedList<>();
        this.q2 = new LinkedList<>();
    }
    
    public void push(int x) {
        // write your code here
        q1.offer(x);
    }

    /*
     * @return: nothing
     */
    public void pop() {
        // write your code here
        while (q1.size() != 1){
            q2.offer(q1.poll());
        }
        q1.poll();
        swap();
    }
    
    public void swap (){
        Queue<Integer> tmp = q1;
        q1 = q2;
        q2 = tmp;
    }

    /*
     * @return: An integer
     */
    public int top() {
        // write your code here
        while (q1.size() != 1){
            q2.offer(q1.poll());
        }
        int res = q1.poll();
        q2.offer(res);
        swap();
        return res;
    }

    /*
     * @return: True if the stack is empty
     */
    public boolean isEmpty() {
        // write your code here
        return q1.isEmpty();
    }
}