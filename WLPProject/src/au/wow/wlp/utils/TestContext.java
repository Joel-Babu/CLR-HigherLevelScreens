/**
 * This class reads the global data from config.propertiesfile
 * 
 *  For eg:
 *  Read the application URL as below,
 *  getContext().getStringProperty(propertyname)
 */
package au.wow.wlp.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class TestContext {
	
	private static Logger log;
	static Properties prop;
	InputStream inp;
	
	public TestContext(Logger log, String file){	
		this.log=log;
		loadPropertiesFile(file);
	}
	
	public void loadPropertiesFile(String file){
		try{
			prop = new Properties();
			inp = new FileInputStream(file);
			prop.load(inp);
//			System.out.println(prop.getProperty("baseURL"));
		}catch(IOException e){
			Assert.fail("Unable to load properties file ", e);
		}finally{
			if(inp!=null){
				try {
					inp.close();
				} catch (IOException e) {
					log.error("Error in closing the stream " , e);
				}
			}
		}
	}
	
	
	public static String getStringProperty(String propertyname){
		String value = null;
		if(prop.containsKey(propertyname)){
			 value = prop.getProperty(propertyname);
		}else
			log.warn("Property: " + propertyname + " is not present in the config.properties file");
		return value;
	}
	
	
	public int getIntProperty(String propertyname){
		int value=0;
		if(prop.containsKey(propertyname)){
			value = Integer.parseInt(prop.getProperty(propertyname));
		}else
			log.warn("Property: " + propertyname + " is not present in the config.properties file");
		return value;
	}
	/*public static void main(String[] args) {
		Properties prop = new Properties();
		InputStream inp = null;
		try{
			inp = new FileInputStream(System.getProperty("user.dir") + "\\TestData\\config.properties");
			prop.load(inp);
			System.out.println(prop.getProperty("baseURL"));
		}catch(IOException e){
			System.out.println(e.getMessage());
		}finally{
			if(inp!=null){
				try {
					inp.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}*/
}
