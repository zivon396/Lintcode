public class MovingAverage {
    /*
    * @param size: An integer
    */
    private int size;
    private Queue<Integer> queue;
    private double sum;
    
    public MovingAverage(int size) {
        // do intialization if necessary
        this.size = size;
        this.queue = new LinkedList<>();
        this.sum = 0;
    }

    /*
     * @param val: An integer
     * @return:  
     */
    public double next(int val) {
        // write your code here
        queue.offer(val);
        sum += val;
        if (queue.size() > size){
            sum -= queue.poll();
        }
        
        return sum / queue.size();
    }
}