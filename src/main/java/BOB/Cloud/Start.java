package BOB.Cloud;

import java.io.IOException;

import BOB.Cloud.provider.manager.PropertiesManager;

public class Start {

	public static void main(String[] args) throws IOException {
		int num = 10;
		String logFormat = "";
		int abnormalRandomValue = 0;

		PropertiesManager p = new PropertiesManager();
		
		num = Integer.parseInt( p.getProperty("logNum"));
        logFormat = p.getProperty("logFormat");
        abnormalRandomValue = Integer.parseInt( p.getProperty("abnormalRandom"));
        
		System.out.println(num +", "+ logFormat + ", " +abnormalRandomValue);
		
		new LogController(num, logFormat, abnormalRandomValue);
		
		
	}

}
