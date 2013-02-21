package BOB.Cloud.provider.manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
	private final String FILENAME = "setting.properties";
	private Properties properties = null;

	public PropertiesManager() {
		FileInputStream propFile = null;
		try {
			propFile = new FileInputStream(FILENAME);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		properties = new Properties(System.getProperties());
		try {
			properties.load(propFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.setProperties(properties);
	}

	public String getProperty(String key) {
		return System.getProperties().getProperty(key);
	}
}
