package BOB.Cloud.consumer;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

public class ConsumerImplement implements Consumer{
	/*
	 * 큐가 가진 배열의 길이는 1000개 이고 배열에 들어가는 자료형은 String 입니다.
	 * 큐에는 절대 Null은 저장하지 마세요 NullPointerException이 발생합니다.
	 * 사용할 메서드는 put(1)-삽입, take(0)-빼냄 [큐가 가득 차거나 비었을 때 대기하는 메서드]	
	 */
	
	/**
	 * Counting how many logs are taken from queue
	 * @see #CONSUMED_LOGS
	 */
	public static BigInteger CONSUMED_LOGS = new BigInteger("0");
	
	/**
	 * A queue statically used in apps.
	 * @see #queue
	 */
	private BlockingQueue<String> queue;
	
	/**
	 * A object which uses send item to Thrift
	 */
	public Network_Manager manager;
	
	/**
	 * A constructor which builds an objects
	 * @param _queue static queue
	 */
	public ConsumerImplement(BlockingQueue<String> _queue){
		this.queue = _queue;
		this.manager = new Network_Manager();
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
				System.out.println("test2 c");
				String _item = queue.take();
				ConsumerImplement.addNumLogs();
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
		
		this.manager.sendItem(item);
		
	}
	
	/**
	 * This method returns the number of consumed logs
	 * It is implemented as a static method.
	 * @return the number of consumed logs
	 */
	public static String getConsumedLogs(){
		return ConsumerImplement.CONSUMED_LOGS.toString();
	}
	
	/**
	 * public static void addNumLogs()
	 * This method adds static variable, CONSUMED_LOGS 
	 * adds 1 value. Its class is BigInteger so, using add method, variable's value is added
	 */
	public static void addNumLogs(){
		ConsumerImplement.CONSUMED_LOGS.add(BigInteger.ONE);
	}
	
}
