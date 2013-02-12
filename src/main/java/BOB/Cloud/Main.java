package BOB.Cloud;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import BOB.Cloud.consumer.Consumer;
import BOB.Cloud.consumer.Consumer_impl;
import BOB.Cloud.provider.Provider;
import BOB.Cloud.provider.ProviderImplement;

public class Main 
{
	/*
	 * 큐가 가진 배열의 길이는 1000개 이고 배열에 들어가는 자료형은 string 입니다.
	 * 1000개 이후부터 저장을 시도하면 빈자리가 생길때 까지 대기 하게 됩니다.
	 * BlockingQueue가 1.5ver 이후부터 지원하기 시작했네요.
	 */
	private static BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1000);
	
    public static void main( String[] args ){    	
    		/* 로그를 10000개 만들 provider를 생성합니다. */
        Provider prv = new ProviderImplement(10000, queue);
       	Consumer con = new Consumer_impl(queue);
    }
}
