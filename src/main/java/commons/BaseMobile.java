package commons;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public abstract class BaseMobile {
	DesiredCapabilities cap;
	PropFileHelper obj;
	WebDriverWait wait;
	protected static AndroidDriver<AndroidElement> driver;
	public  Init mobilepage;
	String filePath;
	public  String osName = System.getProperty("os.name");
	String USERNAME= "localhost";
	String ACCESS_KEY="4723";
	public static  String URLMOBILE;//"http://localhost:4723/wd/hub";// "http://" + USERNAME + ":" + ACCESS_KEY + "/wd/hub/";
	//http://localhost:4723/wd/hub
	public static String platformName, platformVersion, appActivity, appPackage, deviceName; 
	
	/**
	 * @Description Constructor init property file and init cap variables 
	 * @Author Sergio Ramones
	 * @Date 06-SEP-2021 
	 * @Parameter N/A
	 * @return N/A
	 * @throws N/A 
	 */
	public BaseMobile() {
		obj = new PropFileHelper();
		obj.getSystemProp();
		URLMOBILE =System.getProperty("URL_MOBILE");
		platformName = System.getProperty("PLATFORMNAME");
		platformVersion = System.getProperty("PLATFORMVERSION");
		appActivity = System.getProperty("APPACTIVITY");
		appPackage = System.getProperty("APPPACKAGE");
		deviceName = System.getProperty("DEVICENAME");
	}
	
	/**
	 * @Description Constructor init property file and init cap variables 
	 * @Author Sergio Ramones
	 * @Date 06-SEP-2021 
	 * @Parameter N/A
	 * @return N/A
	 * @throws N/A 
	 */
	public BaseMobile(AndroidDriver<AndroidElement> driver) {
		BaseMobile.driver=driver;
	}
	
	/**
	 * @Description set Android driver 
	 * @Author Sergio Ramones
	 * @Date 06-SEP-2021 
	 * @Parameter AndroidDriver<AndroidElement> 
	 * @return N/A
	 * @throws N/A 
	 */
	public void setDriver(AndroidDriver<AndroidElement> driver) {
		BaseMobile.driver=driver;
	}
	
	/**
	 * @Description set init property file and init cap variables 
	 * @Author Sergio Ramones
	 * @Date 06-SEP-2021 
	 * @Parameter N/A
	 * @return N/A
	 * @throws N/A 
	 */
	public void setVariables() {
		PropFileHelper obj = new PropFileHelper();
		obj.getSystemProp();
		URLMOBILE =System.getProperty("URL_MOBILE");
		platformName = System.getProperty("PLATFORMNAME");
		platformVersion = System.getProperty("PLATFORMVERSION");
		appActivity = System.getProperty("APPACTIVITY");
		appPackage = System.getProperty("APPPACKAGE");
		deviceName = System.getProperty("DEVICENAME");
	}

	
	/**
	 * @Description AndroidDriver initialization according to desired capabilities 
	 * @Author Sergio Ramones
	 * @Date 06-SEP-2021 
	 * @Parameter N/A
	 * @return mobileDriver
	 * @throws MalformedURLException 
	 */
	public AndroidDriver<AndroidElement> startMobileApp() throws MalformedURLException {
	
			cap = new DesiredCapabilities();
			cap.setCapability("platformName", platformName);
			cap.setCapability("platformVersion", platformVersion);
			cap.setCapability("appActivity", appActivity);
			cap.setCapability("appPackage", appPackage);
			cap.setCapability("deviceName", deviceName);
			driver = new AndroidDriver<AndroidElement>(new URL(URLMOBILE),cap);

		
		mobilepage = new Init(driver);
		
		return driver;
		
	}//startMobileApp
	
	/**
	 * @Description click on android element 
	 * @Author Sergio Ramones
	 * @Date 06-SEP-2021 
	 * @Parameter AndroidElement
	 * @return N/A
	 * @throws InterruptedException, StaleElementReferenceException
	 * @throws N/A 
	 */
	public void click(AndroidElement element) throws InterruptedException {
		try {
			
			element.click();
			if(element.toString().contains("By.")==true) {
				Reporter.log("Web element was clicked by locatior ---> <b> " + element.toString().split("By.")[1]+"</b> ", true);
			}else if(element.toString().contains("->")==true) {
				Reporter.log("Web element was clicked by locatior ---> <b> " + element.toString().split("->")[1]+"</b> ", true);
			}
			
		} catch (ArrayIndexOutOfBoundsException e) {
			Reporter.log("ArrayIndexOutOfBoundsException: " + element.toString(), true);

			
		} catch (ElementClickInterceptedException e) {
			Reporter.log("Web element is not possible to clicked: " + element.toString(), true);
			e.printStackTrace();
		}
	}//end click
	
	/**
	 * @Description set text on android element 
	 * @Author Sergio Ramones
	 * @Date 08-SEP-2021 
	 * @Parameter AndroidElement, String
	 * @return N/A
	 * @throws InterruptedException, StaleElementReferenceException
	 * @throws N/A 
	 */
	public void setText(AndroidElement element, String text) throws InterruptedException {
		try {
			
			element.sendKeys(text);
			
			Reporter.log("Text was entered: <b><br>[ "+ text+" ]</br></b>", true);
			
		} catch (Exception e) {
			Assert.fail("It's not possible to insert the text: <b><br>[ "+ text+" ]</br></b>");
			e.printStackTrace();
		}
	}//end click
	
	/**
	 * @throws Exception
	 * @Description get text from the webElement
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021 
	 * @Parameter WebElement, String
	 * @return N/A
	 */
	public String getText(AndroidElement element) throws Exception {
		String text = null;
		try {
			
			scroll(element);
			text = element.getText();
			Reporter.log("Text got form application is: <b>[" + text+"] </b>", true);
		} catch (Exception e) {
			Assert.fail("It's not possible to get the text: " + text);
			e.printStackTrace();
		}
		return text;
	}
	
	
	/**
	 * @Description scroll on android element 
	 * @Author Sergio Ramones
	 * @Date 06-SEP-2021 
	 * @Parameter AndroidElement
	 * @return N/A
	 * @throws InterruptedException, StaleElementReferenceException
	 * @throws N/A 
	 */
	public void scroll(AndroidElement element) {
		TouchActions action = new TouchActions(driver);
		action.scroll(element, 10, 100);
		action.perform();
	}
	
	
	/**
	 * @throws Exception
	 * @Description report in the log 
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021 
	 * @Parameter WebElement
	 * @return boolean
	 *
	 **/
	public static void reporter(String text){
		 Reporter.log("Reporter Log <b> [ " + text+ " ] </b>",true);
	 
	}
	
	/**
	 * @Description get Operation System Name
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021 
	 * @Parameter N/A
	 * @return String
	 */
	public String getOSname() {
		if (osName.contains("Mac")) {
			osName = "Mac";
		} else if (osName.contains("Windows")) {
			osName = "Windows";
		} else if (osName.contains("Linux")) {
			osName = "Linux";
		}
		return osName;
	}
	
	/**
	 * @throws N/A
	 * @Description take and screen shoot of specific part during the execution.
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021 
	 * @Parameter N/A 
	 * @return N/A
	 */
	public void takeScreenShot() {
		osName = getOSname();
		switch (osName) {
		case "Mac":
		case "Linux":
			filePath = "/execution_results/mobileScreenshots/";
			break;
		case "Windows":
			filePath = ".\\execution_results\\mobileScreenshots\\";
			break;
		
		}
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//the below method will save the screen shot in the path that we are passing 
		try {	
				String fullpath =  filePath + "mobile"+"_"+formater.format(calendar.getTime())+".png";
				
				FileUtils.copyFile(srcFile, new File(fullpath));
				fullpath = "."+fullpath;
				Reporter.log("******Placed screen shot in: "+ fullpath+" ******",true);
				Reporter.log("<br> <img src='"+ fullpath+"' height='400' with='400'/></b>",true);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws Exception
	 * @Description click in the webElement
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021 
	 * @Parameter List WebElement, text
	 * @return N/A
	 */
	public void selectElementByValue(List<AndroidElement> element, String text) throws Exception {
		try {
			
			for (int i = 0; i <= element.size(); i++) {

				if (i >= element.size()) {
					Assert.fail("The Text is not in the list: " + text);
					break;
				}
 
				if (element.get(i).getText().contains(text)) {
					scroll(element.get(i));
					highlightElement(element.get(i));
					click(element.get(i));
					
					break;
				}

			} // end for
			Reporter.log("The Element in the list was selected: <b>" + text+"</b>");

		} catch (Exception e) {
			Reporter.log("The Element is not the list: <b>" + text+"</b>");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @throws InterrupedException
	 * @Description highlight element
	 * @Author Sergio Ramones
	 * @Date 12-JUN-2021
	 * @Parameter WebElement
	 * @return N/A
	 *
	 **/
	public static void highlightElement(AndroidElement element) throws InterruptedException {
		if (Boolean.parseBoolean(System.getProperty("HIGHLIGH")) == true) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border:  solid red');",element);
			js.executeScript("arguments[0].setAttribute('style', 'border: solid 2px white');", element);
		} else {
			reporter("[ Element won't be highlighed ]");
		}

	}
	/**
	 * @throws Exception
	 * @Description Verify element exist
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021
	 * @Parameter List<AndroidElement>
	 * @return boolean
	 *
	 **/
	public boolean verifyElementExist(List<AndroidElement> element) {
		try {
				element.size();
				return true;
			
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	/**
	 * @throws Exception
	 * @Description Verify element exist
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021 
	 * @Parameter AndroidElement
	 * @return boolean
	 *
	 **/
	public boolean verifyElementExist(AndroidElement element){
		try{
			element.getSize();
            return true;
        }
        catch(NoSuchElementException e){
            return false;
        }
	}
	
	/**
	 * @throws Exception
	 * @Description wait for web element is visible in the page timeout //Duration.ofSeconds(60)
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021
	 * @Parameter WebElement
	 * @return N/A
	 */
	public void reviewElement(AndroidElement element) throws Exception {
		try {
			// set timeout
			wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOf(element));
			highlightElement(element);
			if (element.toString().contains("By.") == true) {
				Reporter.log("Web Element is visible by locatior ---> <b> " + element.toString().split("By.")[1] + "</b> ",true);
			} else if (element.toString().contains("->") == true) {
				Reporter.log("Web Element is visible by locatior ---> <b>" + element.toString().split("->")[1] + "</b> ",true);
			}
		} catch (Exception e) {
			Assert.fail("Web Element is not visible: " + element.toString());
			e.printStackTrace();
		}
	}
	


	
}
