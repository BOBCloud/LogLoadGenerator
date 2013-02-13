package BOB.Cloud.consumer;

import java.util.concurrent.BlockingQueue;

import BOB.Cloud.LogController;
import BOB.Cloud.consumer.Consumer;

/**
 * @author  syncc
 */
public class ConsumerImplement implements Consumer{
	/*
	 * 큐가 가진 배열의 길이는 1000개 이고 배열에 들어가는 자료형은 String 입니다.
	 * 큐에는 절대 Null은 저장하지 마세요 NullPointerException이 발생합니다.
	 * 사용할 메서드는 put(1)-삽입, take(0)-빼냄 [큐가 가득 차거나 비었을 때 대기하는 메서드]	
	 */
	
	/**
	 * A queue statically used in apps.
	 * @see #queue
	 */
	private BlockingQueue<String> queue;
	
	/**
	 * A object which uses send item to Thrift
	 * @uml.property  name="manager"
	 * @uml.associationEnd  
	 */
	public Network_Manager manager;
	
	private int logNum;
	private int threadNum;
	private int endNum;
	private boolean threadFlag = true;
	/**
	 * A constructor which builds an objects
	 * @param CONSUMED_LOGS 
	 * @param _queue static queue
	 */
	public ConsumerImplement(int _logNum ,int _threadNum,BlockingQueue<String> _queue){
		this.logNum = _logNum;
		this.threadNum = _threadNum;
		this.queue = _queue;
		this.manager = new Network_Manager();
		endNum = logNum - threadNum + 1;
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
		while(threadFlag){
			try{
				LogController.addNumLogs();
				detectEnd();
				String _item = queue.take();
				this.handleItem(_item);	
				System.out.println(LogController.getConsumedLogs());
			}catch(InterruptedException e){
				System.out.println("InterruptedException");				
			}
		}
		System.out.println("ConsumerImplement thread End");

	}
	
	private void detectEnd(){
		if(LogController.getConsumedLogs() >= endNum){
			threadFlag = false;
		}
	}
	
	public void handleItem(String item) {
		
		this.manager.sendItem(item);
		
	}
}
