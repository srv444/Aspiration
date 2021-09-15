package commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Init {
	public WebDriver driver;
//	public AndroidDriver<AndroidElement> driverA;

	/**
	 * @throws N/A
	 * @Description constructor for initialize the driver
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021
	 * @Parameter WebDriver
	 * @return N/A
	 */
	public Init(RemoteWebDriver driver) {
		this.driver = driver;
	}

	/**
	 * @throws N/A
	 * @Description constructor for initialize the driver
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021
	 * @Parameter WebDriver
	 * @return N/A
	 */
	public Init(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * @throws N/A
	 * @Description constructor for initialize AndroiDriver
	 * @Author Sergio Ramones
	 * @Date 06-SEP-2021
	 * @Parameter AndroidDriver<AndroidElement>
	 * @return N/A
	 */
	public Init(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
	}

	/**
	 * @throws Exception
	 * @Description get page object and initialize it for WebDriver
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021
	 * @Parameter Class
	 * @return initialized class
	 */
	public <TPage extends Base> TPage getInitPage(Class<TPage> pageClass) {
		try {
			
			Reporter.log("Page Object initializated ---> <b>" + pageClass.getName() +"</b>", true);
			// Initialize the Page with its elements and return it.
			return PageFactory.initElements(driver, pageClass);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}// End getInstance method
	
	/**
	 * @throws Exception
	 * @Description get page object and initialize it for AndroidDRiver
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021
	 * @Parameter Class
	 * @return initialized class
	 */
	public <TPage extends BaseMobile> TPage getMobilePage(Class<TPage> pageClass) throws Exception {
		try {
			
			Reporter.log("Page Object initializated ---> <b>" + pageClass.getName() +"</b>", true);
			// Initialize the Page with its elements and return it.
			PageFactory.initElements(new AppiumFieldDecorator(driver), this);
			return PageFactory.initElements(driver, pageClass); 
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}



}// end class
