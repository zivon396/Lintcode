// 挖槽
class MyThread extends Thread {
	double x;
	public MyThread(double num) {
		super();
		x = num;
	}

	public void run() {
		try {
			Thread.sleep((long)(x*1000));
			Main.printNumber(x);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class Solution {
	public void sleepSort(double[] nums) throws Exception {
		// write your code
		Thread[] threads = new Thread[nums.length];
		for(int i = 0; i < nums.length; i++) {           
			threads[i] = new MyThread(nums[i]);
			threads[i].start();
            
		}
		// must join all the threads to make sure they have same order.
		for (Thread t: threads) {
			t.join();
		}
	}
}
