package BOB.Cloud.provider;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Util
{
	public String getRandomString()
	{
		StringBuffer buffer = new StringBuffer();
		  Random random = new Random();
		 
		  String chars[] = 
		    "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z".split(",");
		 
		  for (int i=0 ; i<chars.length ; i++)
		  {
		    buffer.append(chars[random.nextInt(chars.length)]);
		  }
		 return buffer.toString();
	}
	
	public int getRandomArrayNumber(int length){
		return (int) (Math.random() * length);
	}
	
	public int getRandomNumber(int length){
		return (int) (Math.random() * length) + 1;
	}
	
	public String getRandomIP(){
		Random r = new Random();
		return r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256);
	}

	public String getRandomDate(String format){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
		return simpleDateFormat.format(new Date());
	}
}
