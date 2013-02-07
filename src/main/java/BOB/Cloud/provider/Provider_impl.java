package BOB.Cloud.provider;

import java.util.concurrent.BlockingQueue;

public class Provider_impl implements Provider{
	/*
	 * 큐가 가진 배열의 길이는 1000개 이고 배열에 들어가는 자료형은 String 입니다.
	 * 큐에는 절대 Null은 저장하지 마세요 NullPointerException이 발생합니다.
	 * 사용할 메서드는 put(1)-삽입, take(0)-빼냄 [큐가 가득 차거나 비었을 때 대기하는 메서드]	
	 */
	private BlockingQueue<String> queue;
		
	/* 로그를 몇개나 만들지,, 생성자에서 넣어줍니다. */
	private int logNum;
	
	public Provider_impl(int _logNum, BlockingQueue<String> _queue){
		this.logNum = _logNum;
		this.queue 	= _queue;
	}

	/* 스레드를 만들어 줍니다. */
	@Override
	public void run() {
		while(true){
			/* queue.put(String) 이렇게 하면 로그를 넣을수 있습니다. */
		}
		
	}	
	
	
}
