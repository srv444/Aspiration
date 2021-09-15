package commons;

import java.util.Properties;

import org.testng.Reporter;




public class PropFileHelper {	

	private static Properties systemProp = null;
	
	/**
	 * @throws N/A
	 * @Description Constructor read system properties file 
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021
	 * @Parameter N/A
	 * @return N/A
	 * @throws N/A 
	 */
	public PropFileHelper() {
		getSystemProp();
	}
	
	/**
	 * @throws N/A
	 * @Description load properties file stored in Constant class
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021
	 * @Parameter N/A
	 * @return String
	 * @throws N/A 
	 */
	public Properties getSystemProp() {
		String filename = Constants.systemPropFileName;
		if(systemProp==null)
			systemProp = getPropertiesFromFileName(filename);
		
		loadSystemProp(filename, systemProp);
		
		return systemProp;
	}

	/**
	 * @throws N/A
	 * @Description return properties from file name
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021
	 * @Parameter String
	 * @return Properties
	 * @throws N/A 
	 */
	public Properties getPropertiesFromFileName(String filename) {
		
		Properties prop = null;
		
		try{
			PropertiesFile propFile = new PropertiesFile(filename);
			prop = propFile.getProperties();
		} catch (Exception e) {	
			Reporter.log("Unable to read properties file, name:[ " + filename + " ]error");
			System.exit(1);
		}
		
		return prop;
	}
	
	/**
	 * @throws N/A
	 * @Description load properties from file name and Properties.
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021
	 * @Parameter String, Properties
	 * @return N/A
	 * @throws N/A 
	 */
	private void loadSystemProp(String filename, Properties prop) {
		
		if(System.getProperties().isEmpty())
			System.load(filename);
		else {
			System.getProperties().putAll(prop);
		}
	}


	/**
	 * @throws N/A
	 * @Description return specific property and Properties file.
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021
	 * @Parameter Properties, String
	 * @return N/A
	 * @throws N/A 
	 */
	public String getPropertyValue(Properties prop, String propName) {
		String retVal = null;
	
		try{
			retVal = prop.getProperty(propName).trim();
		} catch (Exception e) {
			System.out.println("Value can't be readed.");;
		}
		return retVal;
	}
	
 	
}
