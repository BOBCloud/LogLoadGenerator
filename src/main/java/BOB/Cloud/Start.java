package BOB.Cloud;

import java.util.Scanner;

public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int num = 0;
		String logFormat = "";
		int abnormalRandomValue = 0;
		boolean flag = true;
		String input;
		
		while(flag){
			Scanner sc = new Scanner(System.in);
		
			System.out.print("<BOB_Cloud LogLoadGenerater> ");
			input = sc.nextLine();
			if(input.matches("(?i).*format.*")){
				logFormat = input.substring( input.indexOf(" ") +1 );
				System.out.println("Set format : " + logFormat);
				
			}else if(input.matches("(?i).*count.*")){
				num = Integer.parseInt(input.substring( input.indexOf(" ") +1 ));
				System.out.println("Set count : " + num);
				
			}else if(input.matches("(?i).*abRandom.*")){
				abnormalRandomValue = Integer.parseInt( input.substring( input.indexOf(" ") +1 ));
				if( (0 <= abnormalRandomValue) && (abnormalRandomValue >= 100) ){
					System.out.println("input Error, range 0 ~ 100");
				}
				System.out.println("Set abRandom : " + abnormalRandomValue);
				
			}else if(input.matches("(?i).*show")){
				System.out.println("Formate  : " + logFormat);
				System.out.println("Count	 : " + num);
				System.out.println("abRandom : " + abnormalRandomValue);
				
			}else if(input.matches("(?i).*help.*")){
				System.out.println("Formate 	<String> 			: 로그포멧을 지정합니다.");
				System.out.println("Count  		<num>[0~999*] 	 	: 로그를 몇개나 보낼지 설정합니다");
				System.out.println("abRandom  	<percent>[0~100] 	: 비정상 로그를 보낼 확률을 설정합니다");
				

			}else if(input.matches("(?i).*run")){
				System.out.println("Strat");
				if( (!logFormat.equals("")) && num !=0 && abnormalRandomValue != 0){
				new LogController(num, logFormat, abnormalRandomValue);
				}else{
					System.out.println("Need more value");
				}
			}
			else{
				System.out.println(input +" : Command not found");
			}
		
			
	
		}
	}
}
