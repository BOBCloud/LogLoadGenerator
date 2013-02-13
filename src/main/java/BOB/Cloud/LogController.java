package BOB.Cloud;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import BOB.Cloud.consumer.ConsumerImplement;
import BOB.Cloud.provider.ProviderImplement;

public class LogController {
	/*
	 * 큐가 가진 배열의 길이는 1000개 이고 배열에 들어가는 자료형은 string 입니다. 1000개 이후부터 저장을 시도하면 빈자리가
	 * 생길때 까지 대기 하게 됩니다. BlockingQueue가 1.5ver 이후부터 지원하기 시작했네요.
	 */
	private static BlockingQueue<String> queue = new ArrayBlockingQueue<String>(
			100000);
	/* Log를 10000개를 요청할 때마다, Thread를 하나씩 만듭니다 */
	private final static int threadDivide = 10;

	/**
	 * Counting how many logs are taken from queue
	 * @see #CONSUMED_LOGS
	 */
	public static long CONSUMED_LOGS = 0;
	
	/**
	 * This method returns the number of consumed logs
	 * It is implemented as a static method.
	 * @return the number of consumed logs
	 */
	public synchronized static long getConsumedLogs(){
		return CONSUMED_LOGS;
	}
	
	/**
	 * public static void addNumLogs()
	 * This method adds static variable, CONSUMED_LOGS 
	 * adds 1 value. Its class is BigInteger so, using add method, variable's value is added
	 */
	public synchronized static void addNumLogs(){
		CONSUMED_LOGS++;
	}
	
	public LogController(int logNum) {

		int threadNum;
		int logNumThread = logNum;

		if ((logNum % threadDivide) != 0) {
			threadNum = (logNum / threadDivide) + 1;
		} else {
			threadNum = logNum / threadDivide;
		}

		//ConsoleUI bar = new ConsoleUI(logNum, "Log Load Generator");
		//new Thread(bar).start();		
		
		for (int i = 0; i < threadNum; i++) {
			new Thread(new ProviderImplement(
					((logNum - threadDivide) > 0) ? threadDivide : logNum,
					queue)).start();
			new Thread(new ConsumerImplement(logNumThread, threadNum, queue)).start();
			logNum = logNum - threadDivide;
		}
	}
}