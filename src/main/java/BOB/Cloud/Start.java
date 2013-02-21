package BOB.Cloud;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class Start {

	 public static String getPropertyy() throws Exception {

		InputStream in = ClassLoader
				.getSystemResourceAsStream("setting.properties");
		if (in == null) {
			throw new Exception("Cannot find test.properties");
		}
		Properties p = new Properties();
		p.load(in);
		in.close();
		return p.getProperty("modelFile");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		int num = 50;
		String logFormat = "";
		int abnormalRandomValue = 50;
		boolean flag = true;
		String input;

		try {
			System.out.println(getPropertyy());
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}

		/*
		 * ClassLoader cl; cl = Thread.currentThread().getContextClassLoader();
		 * if (cl == null) cl = ClassLoader.getSystemClassLoader();
		 * 
		 * URL url = cl.getResource("setting.properties");
		 * 
		 * Properties properties = new Properties();
		 * 
		 * properties.load(url.openStream()); properties.list(System.out);
		 */
		// new LogController(num, logFormat, abnormalRandomValue);

	}

	private static char[] getProperty() {
		// TODO Auto-generated method stub
		return null;
	}
}
