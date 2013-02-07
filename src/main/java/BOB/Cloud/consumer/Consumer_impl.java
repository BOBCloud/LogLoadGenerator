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
	private BlockingQueue<String> queue;
		
	public Consumer_impl(BlockingQueue<String> _queue){
		this.queue = _queue;
	}

	/* 스레드를 만들어 줍니다 */
	@Override
	public void run() {
		while(true){
			/* queue.take() 이렇게 하면 큐에 들어가 있는 로그가 나옵니다. */
		}		
	}	
	
}
