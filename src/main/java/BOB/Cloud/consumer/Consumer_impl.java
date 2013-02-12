package BOB.Cloud.consumer;

import java.util.concurrent.BlockingQueue;
/*
 * 메이븐에 netty를 추가해 놨습니다. 비동기 처리가 가능하기 때문에 대용량 데이터를 전송하는
 * 우리 프로젝트에 사용하면 좋을 듯 싶습니다.
 */

public class Consumer_impl implements Consumer{
	/*
	 * 큐가 가진 배열의 길이는 1000개 이고 배열에 들어가는 자료형은 String 입니다.
	 * 큐에는 절대 Null은 저장하지 마세요 NullPointerException이 발생합니다.
	 * 사용할 메서드는 put(1)-삽입, take(0)-빼냄 [큐가 가득 차거나 비었을 때 대기하는 메서드]	
	 */
	
	/**
	 * Counting how many logs are taken from queue
	 * @see #CONSUMED_LOGS
	 */
	public static int CONSUMED_LOGS = 0;
	
	/**
	 * A queue statically used in apps.
	 * @see #queue
	 */
	private BlockingQueue<String> queue;
	
	/**
	 * A constructor which builds an objects
	 * @param _queue static queue
	 */
	public Consumer_impl(BlockingQueue<String> _queue){
		this.queue = _queue;
	}

	/**
	 * public void run()
	 * This method is implemented automatically because of implementing Runnable interface
	 * Inside method, Try clause continuously takes logs from the blocking queue.
	 * Blocking queue automatically send InterruptedException for ideal distribution
	 * If this object get it, then call sleep function for sleeping 0.5 seconds.
	 * We assume that in 0.5 seconds, queue will be filled.
	 */
	public void run() {
		while(true){
			try{
				String _item = queue.take();
				CONSUMED_LOGS ++;
				this.handleItem(_item);
			}catch(InterruptedException e){
				try{
					Thread.sleep(500);
				}catch(Exception _e){
					System.out.println("Little bit wait..");
				}
			}
			/* queue.take() 이렇게 하면 큐에 들어가 있는 로그가 나옵니다. */
		}		
	}
	
	public void handleItem(String item) {
		// TODO Auto-generated method stub
		
	}	
	
}
