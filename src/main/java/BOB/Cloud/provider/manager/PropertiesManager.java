package BOB.Cloud.provider.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager
{
	private final String FILENAME = "setting.properties";
	private Properties properties = null;

	public PropertiesManager() 
	{
		properties = new Properties();
		FileInputStream file = null;
		try 
		{
			file = new FileInputStream(FILENAME);
			properties.load(file);
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}
}
